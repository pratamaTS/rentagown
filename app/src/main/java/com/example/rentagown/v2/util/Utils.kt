package com.example.rentagown.v2.util

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.Exception
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {


    val LOCALE = Locale("in", "ID")

    val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", LOCALE)
    val DATE_TIME_FORMAT2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", LOCALE)
    val DATE_FORMAT_PRODUCT = DateTimeFormatter.ofPattern("dd'\\'MM'\\'yyyy", LOCALE)
    val DATE_FORMAT_PAYMENT_DEADLINE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", LOCALE)
    val DATE_TIME_FORMAT_PAYMENT_DEADLINE = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm:ss", LOCALE)
    val DATE_TIME_FORMAT_PAYMENT_DEADLINE2 = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", LOCALE)
    val DATE_FORMAT_CREATE_BOOKING = DateTimeFormatter.ofPattern("dd-MM-yyyy", LOCALE)
    val DATE_FORMAT_MY_BOOKING = DateTimeFormatter.ofPattern("dd MMM yyyy", LOCALE)

    val currIDR: DecimalFormat = DecimalFormat.getCurrencyInstance() as DecimalFormat
    val formatIDR = DecimalFormatSymbols()

    fun formatMoney(money: Long?, defaultValue: String? = null, defaultIfZero: Boolean = false): String? {
        if(money == null) return defaultValue
        if(money == 0L && defaultIfZero) return defaultValue

        return currIDR.format(money)
    }

    fun formatDateBooking(date: LocalDate): String {
        return DATE_FORMAT_MY_BOOKING.format(date)
    }

    fun formatDateCreateBooking(date: LocalDate): String {
        return DATE_FORMAT_CREATE_BOOKING.format(date)
    }

    fun getGlideException(): RequestListener<Drawable> {
        return object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }
    }

    fun formatMyBookingStartEndDate(dtStartStr: String?, dtEndStr: String?, parseFormat: DateTimeFormatter? = DATE_TIME_FORMAT): String {
        if(dtStartStr.isNullOrBlank() && dtEndStr.isNullOrBlank()) return "-"

        val dtStart = try { LocalDate.parse(dtStartStr, parseFormat) } catch (_: Exception) { null }
        val dtEnd = try { LocalDate.parse(dtEndStr, parseFormat) } catch (_: Exception) { null }

        val sb = StringBuilder()

        sb.append(if(dtStart != null) DATE_FORMAT_MY_BOOKING.format(dtStart) else "")

        if(dtEnd != null && dtStart?.compareTo(dtEnd) != 0) {
            sb.append(" - ")
            .append(DATE_FORMAT_MY_BOOKING.format(dtEnd))
        }

        return sb.toString()
    }

    fun formatDate(dtStr: String?, parseFormatter: DateTimeFormatter = DATE_TIME_FORMAT, formatter: DateTimeFormatter = DATE_FORMAT_MY_BOOKING): String? {
        if(dtStr.isNullOrBlank()) return "-"

        val dt = try { LocalDate.parse(dtStr, parseFormatter) } catch (_: Exception) { null }

        return if(dt != null) formatter.format(dt) else ""
    }

    fun formatDateTime(dtStr: String?, parseFormatter: DateTimeFormatter = DATE_TIME_FORMAT, formatter: DateTimeFormatter = DATE_FORMAT_MY_BOOKING): String? {
        if(dtStr.isNullOrBlank()) return "-"

        val dt = try { LocalDateTime.parse(dtStr, parseFormatter) } catch (_: Exception) { null }

        return if(dt != null) formatter.format(dt) else ""
    }

    fun parseDate(dtStr: String?, parseFormat: DateTimeFormatter? = DATE_TIME_FORMAT): LocalDate? {
        if(dtStr.isNullOrBlank()) return null

        return try { LocalDate.parse(dtStr, parseFormat) } catch (_: Exception) { null }
    }

    fun parseDateTime(dtStr: String?, parseFormat: DateTimeFormatter? = DATE_TIME_FORMAT): LocalDateTime? {
        if(dtStr.isNullOrBlank()) return null

        return try { LocalDateTime.parse(dtStr, parseFormat) } catch (_: Exception) { null }
    }

    init {
        formatIDR.currencySymbol = "Rp "
        formatIDR.monetaryDecimalSeparator = ','
        formatIDR.groupingSeparator = '.'

        currIDR.decimalFormatSymbols = formatIDR
        currIDR.maximumFractionDigits = 0
        currIDR.minimumFractionDigits = 0

    }

}
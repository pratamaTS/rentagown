package com.example.rentagown.v2.util

import android.graphics.Bitmap
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
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {


    val LOCALE = Locale("in", "ID")

    val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val DATE_TIME_FORMAT2 = DateTimeFormatter.ofPattern("dd'\\'MM'\\'yyyy")
    val DATE_TIME_FORMAT_BOOKING = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val DATE_TIME_FORMAT_MY_BOOKING = DateTimeFormatter.ofPattern("dd MMM yyyy", LOCALE)

    val currIDR: DecimalFormat = DecimalFormat.getCurrencyInstance() as DecimalFormat
    val formatIDR = DecimalFormatSymbols()

    fun formatMoney(money: Long?): String? {
        if (money == null) return null

        return currIDR.format(money)
    }

    fun formatDateBooking(date: LocalDate): String {
        return DATE_TIME_FORMAT_BOOKING.format(date)
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

    fun formatMyBookingStartEndDate(dtStartStr: String?, dtEndStr: String?): String {
        if(dtStartStr.isNullOrBlank() && dtEndStr.isNullOrBlank()) return "-"

        val dtStart = try { LocalDate.parse(dtStartStr, DATE_TIME_FORMAT) } catch (_: Exception) { null }
        val dtEnd = try { LocalDate.parse(dtEndStr, DATE_TIME_FORMAT) } catch (_: Exception) { null }

        val sb = StringBuilder()

        sb.append(if(dtStart != null) DATE_TIME_FORMAT_MY_BOOKING.format(dtStart) else "")

        if(dtEnd != null && dtStart?.compareTo(dtEnd) != 0) {
            sb.append(" - ")
            .append(DATE_TIME_FORMAT_MY_BOOKING.format(dtEnd))
        }

        return sb.toString()
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
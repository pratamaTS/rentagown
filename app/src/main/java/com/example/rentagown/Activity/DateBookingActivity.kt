package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.applikeysolutions.cosmocalendar.view.CalendarView
import com.example.rentagown.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class DateBookingActivity : AppCompatActivity(), View.OnClickListener {
    var calendarView: CalendarView? = null
    var startDate: String? = null
    var endDate: String? = null
    var btnBack: ImageButton? = null
    var selectDate: ImageButton? = null
    val days: MutableSet<Long> = TreeSet()
    var idProduct: String? = null
    var productCategory: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var productFinalPrice: Int? = null
    var productPromoAmount: Int? = null
    var productQuantity: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_booking)

        //INIT VIEW
        calendarView = findViewById(R.id.calendar_view)
        btnBack = findViewById(R.id.im_close)
        selectDate = findViewById(R.id.im_set_date)

        idProduct = intent.getStringExtra("id_product")
        productCategory = intent.getStringExtra("name_product_category")
        productName = intent.getStringExtra("product_name")
        productPrice = intent.getIntExtra("product_price",0)
        productFinalPrice = intent.getIntExtra("final_price",0)
        productPromoAmount = intent.getIntExtra("promo_amount",0)
        productQuantity = intent.getIntExtra("product_quantity",0)

        //Set First day of the week
        calendarView!!.setFirstDayOfWeek(Calendar.SUNDAY)

        //Set Orientation 0 = Horizontal | 1 = Vertical
        calendarView!!.setCalendarOrientation(1)
        calendarView!!.setWeekendDays(object : HashSet<Long?>() {
            init {
                Calendar.SUNDAY.toLong()
            }
        })

        //Range SelectionType
        calendarView!!.setSelectionType(SelectionType.RANGE)

        //Set days you want to connect
        val calendar = Calendar.getInstance()
        days.add(calendar.timeInMillis)

        //Define colors
        val textColor = Color.parseColor("#E6B31E")
        val selectedTextColor = Color.parseColor("#FFFFFF")
        val disabledTextColor = Color.parseColor("#707070")
        val connectedDays = ConnectedDays(days, textColor, selectedTextColor, disabledTextColor)

        //Connect days to calendar
        calendarView!!.addConnectedDays(connectedDays)
        calendarView!!.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM)
        btnBack!!.setOnClickListener(this)
        selectDate!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_close -> {
                onBackPressed()
            }
            R.id.im_set_date -> {
//                val product = Intent(v.context, ViewProductActivity::class.java)
//                product.putExtra("id_product", newGownList[position].idProduct)
//                product.putExtra("start_date", newGownList[position].idProduct)
//                v.context.startActivity(product)
                if (calendarView!!.selectionManager is RangeSelectionManager) {
                    val rangeSelectionManager =
                        calendarView!!.selectionManager as RangeSelectionManager
                    if (rangeSelectionManager.days != null) {
                        // Get Start Date
                        startDate = rangeSelectionManager.days.first.toString()
                        startDate = startDate!!.replace("Day{day=", "")
                        startDate = startDate!!.replace("}", "")

                        // Get End Date
                        endDate = rangeSelectionManager.days.second.toString()
                        endDate = endDate!!.replace("Day{day=", "")
                        endDate = endDate!!.replace("}", "")

                        // Format Date
                        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
                        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
                        val startDateNew: Date = inputFormat.parse(startDate)
                        val endDateNew: Date = inputFormat.parse(endDate)
                        val startDateConvert: String = outputFormat.format(startDateNew)
                        val endDateConvert: String = outputFormat.format(endDateNew)

                        Log.d("start days new", startDateConvert)
                        Log.d("end days new", endDateConvert)

                        val viewProduct = Intent(this, ViewProductActivity::class.java)

                        viewProduct.putExtra("id_product", idProduct)
                        viewProduct.putExtra("name_product_category", productCategory)
                        viewProduct.putExtra("product_name", productName)
                        viewProduct.putExtra("product_price", productPrice)
                        viewProduct.putExtra("final_price", productFinalPrice)
                        viewProduct.putExtra("promo_amount", productPromoAmount)
                        viewProduct.putExtra("product_quantity", productQuantity)
                        viewProduct.putExtra("start_date", startDateConvert)
                        viewProduct.putExtra("end_date", endDateConvert)

                        startActivity(viewProduct)
                        finish()

                    } else {
                        Toast.makeText(this, "Invalid Selection", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}
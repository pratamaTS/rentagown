package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.applikeysolutions.cosmocalendar.view.CalendarView
import com.example.rentagown.R
import java.util.*

class DateBookingActivity : AppCompatActivity(), View.OnClickListener {
    var calendarView: CalendarView? = null
    var startDate: String? = null
    var endDate: String? = null
    var selectDate: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_booking)

        //INIT VIEW
        calendarView = findViewById(R.id.calendar_view)
        selectDate = findViewById(R.id.im_set_date)

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
        calendarView!!.setSelectionType(SelectionType.SINGLE)

        //Set days you want to connect
        val calendar = Calendar.getInstance()
        val days: MutableSet<Long> = TreeSet()
        days.add(calendar.timeInMillis)

        //Define colors
        val textColor = Color.parseColor("#E6B31E")
        val selectedTextColor = Color.parseColor("#FFFFFF")
        val disabledTextColor = Color.parseColor("#707070")
        val connectedDays = ConnectedDays(days, textColor, selectedTextColor, disabledTextColor)

        //Connect days to calendar
        calendarView!!.addConnectedDays(connectedDays)
        calendarView!!.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM)
        selectDate!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_close -> {
                finish()
            }
            R.id.im_set_date -> {
            }
        }
    }
}
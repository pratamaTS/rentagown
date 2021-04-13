package com.example.rentagown.v2.ui.choosedate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.applikeysolutions.cosmocalendar.model.Day
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener2
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.selection.SelectionState
import com.applikeysolutions.cosmocalendar.utils.DateUtils
import com.applikeysolutions.cosmocalendar.view.CalendarView
import com.example.rentagown.Connection.Interface.CheckDateInterface
import com.example.rentagown.Connection.Presenter.CheckDatePresenter
import com.example.rentagown.R
import com.example.rentagown.Response.CheckDate.DataCheckDate
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.ReqCheckDate
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.invoicedetail.InvoiceDetailPresenter
import java.util.*

class ChooseDateActivity : BaseRAGActivity<ChooseDateContract.Presenter>(), ChooseDateContract.View, View.OnClickListener, CheckDateInterface {

    companion object {

        const val REQ_PICK_DATE_RANGE = 1523
        const val RES_DATE_PICKED = 1524

    }

    override val layoutId: Int = R.layout.activity_choose_date_v2
    override var btnBackId: Int = R.id.btn_back


    override lateinit var presenter: ChooseDateContract.Presenter

    private lateinit var cvBookingCalendar: CalendarView
    private lateinit var btnChoose: ImageButton

    private var selectedStartDate: Calendar? = null
    private var selectedEndDate: Calendar? = null

    override fun init() {
        super.init()
        presenter = ChooseDatePresenter(
                RepositoryLocator
                        .getInstance(
                                RemoteRepositoryLocator
                                        .getInstance(
                                                RAGApi
                                                        .apiService(this)
                                        )
                        )
                        .bookingRepository
        )
    }

    override fun setupWidgets() {
        super.setupWidgets()

        cvBookingCalendar = findViewById(R.id.cv_booking_calendar)
        btnChoose = findViewById(R.id.btn_choose)

        setupCalendar()

        btnChoose.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedStartDateMillis = getSelectedStartDate()
        val selectedEndDateMillis = getSelectedEndDate()

        selectedStartDate = if(selectedStartDateMillis > 0) DateUtils.getCalendar(selectedStartDateMillis) else null
        selectedEndDate = if(selectedEndDateMillis > 0) DateUtils.getCalendar(selectedEndDateMillis) else null

        presenter.setPreselectedDates(selectedStartDate, selectedEndDate)
    }

    private fun setupCalendar() {
        cvBookingCalendar.selectionManager = RangeSelectionManager(object : OnDaySelectedListener2 {
            override fun onDaySelected(day: Day) {
                val selectionManager = cvBookingCalendar.selectionManager
                if(selectionManager is RangeSelectionManager) {
                    if(selectionManager.getSelectedState(day) == SelectionState.START_RANGE_DAY_WITHOUT_END) {
                        selectedStartDate = day.calendar
                        selectedEndDate = null
                    } else if(selectionManager.days != null) {
                        selectedStartDate = selectionManager.days.first?.calendar
                        selectedEndDate = selectionManager.days.second?.calendar
                    }
                }
            }
        })

        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DATE, 90)

        val minDate = Calendar.getInstance()

        cvBookingCalendar.minDate = minDate
        cvBookingCalendar.maxDate = maxDate

        cvBookingCalendar.showRangeSelection = false

    }

    private fun getSelectedProductId() = intent.getStringExtra("product_id")

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_choose -> {
                val selectionManager = cvBookingCalendar.selectionManager
                if(selectionManager is RangeSelectionManager) {
                    presenter.chooseDate(getSelectedProductId().toString(), selectedStartDate, selectedEndDate)
                }
            }
        }
    }

    override fun setResultDateSelected(startDateMillis: Long, endDateMillis: Long) {
        Intent().apply {
            putExtra("selected_start_date", startDateMillis)
            putExtra("selected_end_date", endDateMillis)

            setResult(RES_DATE_PICKED, this)
            finish()
        }
    }

    override fun showMessage(msg: String) {
        Log.d("check date", msg)
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun checkingDate(productID: String, reqCheckDate: ReqCheckDate) {
        CheckDatePresenter(this).checkDate(this, reqCheckDate, productID)
    }

    override fun selectDate(calendar: Calendar) {
        val selectionManager = cvBookingCalendar.selectionManager
        if(selectionManager is RangeSelectionManager) {
            val day = Day(calendar)
            if(!selectionManager.isDaySelected(day)) {
                selectionManager.toggleDay(day)
            }
        }
    }

    private fun getSelectedStartDate() = intent.getLongExtra("selected_start_date", -1)
//    private fun getSelectedStartDate() = intent.getLongExtra("selected_start_date", 1614145628098)

    private fun getSelectedEndDate() = intent.getLongExtra("selected_end_date", -1)

    override fun onSuccessCheckDate(dataCheckDate: DataCheckDate) {
        presenter.setSuccesCheckingDate(selectedStartDate, selectedEndDate)
    }

    override fun onErrorCheckDate(msg: String) {
        presenter.closeLoading()
        showMessage(msg)
    }
//    private fun getSelectedEndDate() = intent.getLongExtra("selected_end_date", 1614318428098)

}
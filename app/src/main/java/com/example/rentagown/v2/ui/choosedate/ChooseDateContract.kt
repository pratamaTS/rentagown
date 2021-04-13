package com.example.rentagown.v2.ui.choosedate

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.ReqCheckDate
import java.util.*

class ChooseDateContract {

    interface View : BaseView<Presenter> {

        fun setResultDateSelected(startDateMillis: Long, endDateMillis: Long)
        fun showMessage(msg: String)
        fun checkingDate(productID: String, reqCheckDate: ReqCheckDate)
        fun selectDate(calendar: Calendar)

    }

    interface Presenter : BasePresenter {

        fun setPreselectedDates(startDate: Calendar?, endDate: Calendar?)
        fun setSuccesCheckingDate(startDate: Calendar?, endDate: Calendar?)
        fun closeLoading()
        fun chooseDate(productID: String, startDate: Calendar?, endDate: Calendar?)

    }

}
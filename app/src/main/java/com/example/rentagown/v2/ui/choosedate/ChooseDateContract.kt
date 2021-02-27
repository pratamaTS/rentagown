package com.example.rentagown.v2.ui.choosedate

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import java.util.*

class ChooseDateContract {

    interface View : BaseView<Presenter> {

        fun setResultDateSelected(startDateMillis: Long, endDateMillis: Long)
        fun selectDate(calendar: Calendar)

    }

    interface Presenter : BasePresenter {

        fun setPreselectedDates(startDate: Calendar?, endDate: Calendar?)
        fun chooseDate(startDate: Calendar?, endDate: Calendar?)

    }

}
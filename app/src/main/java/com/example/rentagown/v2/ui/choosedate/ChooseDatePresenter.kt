package com.example.rentagown.v2.ui.choosedate

import com.example.rentagown.v2.base.BaseRAGPresenter
import java.util.*

class ChooseDatePresenter : BaseRAGPresenter<ChooseDateContract.View>(), ChooseDateContract.Presenter {

    override fun setPreselectedDates(startDate: Calendar?, endDate: Calendar?) {
        startDate?.apply { view?.selectDate(this) }
        endDate?.apply { view?.selectDate(this) }
    }

    override fun chooseDate(startDate: Calendar?, endDate: Calendar?) {
        val mStartDate = startDate
        val mEndDate = endDate

        if(mStartDate != null && mEndDate != null) {
            // start end different date
            view?.setResultDateSelected(mStartDate.timeInMillis, mEndDate.timeInMillis)
        } else if(mStartDate != null && mEndDate == null) {
            // one day
            view?.setResultDateSelected(mStartDate.timeInMillis, mStartDate.timeInMillis)
        }

    }

}
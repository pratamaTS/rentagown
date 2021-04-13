package com.example.rentagown.v2.ui.choosedate

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.DateResponse
import com.example.rentagown.v2.data.model.ReqCheckDate
import com.example.rentagown.v2.data.source.BookingDataSource
import java.text.SimpleDateFormat
import java.util.*

class ChooseDatePresenter(private val repository: BookingDataSource) : BaseRAGPresenter<ChooseDateContract.View>(), ChooseDateContract.Presenter {

    override fun setPreselectedDates(startDate: Calendar?, endDate: Calendar?) {
        startDate?.apply { view?.selectDate(this) }
        endDate?.apply { view?.selectDate(this) }
    }

    override fun setSuccesCheckingDate(startDate: Calendar?, endDate: Calendar?) {
        val mStartDate = startDate
        val mEndDate = endDate

        if(mStartDate != null && mEndDate != null) {
            view?.showLoading(false)
            view?.setResultDateSelected(mStartDate.timeInMillis, mEndDate.timeInMillis)
        }
    }

    override fun closeLoading() {
        view?.showLoading(false)
    }

    override fun chooseDate(productID: String, startDate: Calendar?, endDate: Calendar?) {
        val mStartDate = startDate
        val mEndDate = endDate

        if(mStartDate != null && mEndDate != null) {
            // start end different date
            // Format Date
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val convertStartDate = formatter.format(mStartDate.timeInMillis)
            val convertEndDate = formatter.format(mEndDate.timeInMillis)

            view?.showLoading(true)

            val reqCheckDate = ReqCheckDate(
                    startDate = convertStartDate,
                    endDate = convertEndDate
            )

//            safeCallDate(repository.checkDate(productID, reqCheckDate), object : ListenerDate {
//                override fun onSuccess(message: String?) {
//                    view?.setResultDateSelected(mStartDate.timeInMillis, mEndDate.timeInMillis)
//                }
//
//                override fun onFailed(message: String?) {
//                    Log.d("failed date", message.toString())
//                    view?.showMessage(message)
//                }
//
//            }, RequestConfiguration(updateLoadingContentIndicator = false))

            view?.checkingDate(productID, reqCheckDate)
        } else if(mStartDate != null && mEndDate == null) {
            // one day
            view?.showLoading(true)
            Handler(Looper.getMainLooper()).postDelayed({
                view?.showLoading(false)
                view?.setResultDateSelected(mStartDate.timeInMillis, mStartDate.timeInMillis)
            }, 1000)
        }

    }

}
package com.example.rentagown.v2.ui.home.mybookings

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.BookingStatusEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.source.BookingDataSource

class MyBookingsPresenter(private val context: Context, private val repository: BookingDataSource) : BaseRAGPresenter<MyBookingsContract.View>(), MyBookingsContract.Presenter {

    override fun start() {
        super.start()

        loadMyBookings()
    }

    override fun loadMyBookings() {
        var token: String? = null
        val sessionManager = SessionManager(context)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }
        
        if(token != null){
            view?.showLoadingContent(true)
            safeCallPaging(repository.getMyBookings(), object : Listener<List<Booking>> {
                override fun onSuccess(data: List<Booking>?) {
                    val mData = data ?: listOf()

                    view?.showEmptyPlaceHolder(mData.isEmpty())
                    view?.showMyBookings(mData)
                }

            }, RequestConfiguration(showErrorMessage = false, updateLoadingIndicator = false))
        }else{
            view?.showLoadingContent(false)
            view?.showEmptyPlaceHolder(true)
        }
    }

    override fun onBtnBrowserClicked() {
        view?.navigateToBrowse()
    }

    override fun onBtnBookingHistoryClicked() {
        view?.navigateToBookingHistory()
    }

    override fun onItemClicked(booking: Booking) {
        view?.navigateToBookingDetail(booking)
    }

    override fun onBtnFittingClicked(booking: Booking) {
        booking.transactionId?.let { trxId ->
            view?.navigateToFitting(trxId, booking.fittingId)
        }
    }

    override fun onFittingSaved(transactionId: String?, fittingId: String?) {
        transactionId?.apply {
            view?.updateFittingData(this, fittingId)
        }
    }

    override fun onBookingDataChanged(booking: Booking?) {
        booking?.apply {
            if(BookingStatusEnum.isCompleted(status) || BookingStatusEnum.isCancelled(status)) {
                view?.removeBookingData(this)
            } else {
                view?.updateBookingData(this)
            }
        }
    }

    override fun onPaymentConfirmed(booking: Booking?) {
        booking?.apply { view?.updateBookingData(this) }
    }

}
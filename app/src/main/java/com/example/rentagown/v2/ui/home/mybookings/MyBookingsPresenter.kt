package com.example.rentagown.v2.ui.home.mybookings

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.source.BookingDataSource

class MyBookingsPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<MyBookingsContract.View>(), MyBookingsContract.Presenter {

    override fun start() {
        super.start()

        loadMyBookings()
    }

    override fun loadMyBookings() {
        view?.showLoadingContent(true)

        safeCallPaging(repository.getMyBookings(), object : Listener<List<Booking>> {
            override fun onSuccess(data: List<Booking>?) {
                val mData = data ?: listOf()

                view?.showEmptyPlaceHolder(mData.isEmpty())
                view?.showMyBookings(mData)
            }

        }, RequestConfiguration(showErrorMessage = false, updateLoadingIndicator = false))
    }

    override fun onBtnBrowserClicked() {
        view?.navigateToBrowse()
    }

    override fun onBtnBookingHistoryClicked() {
        view?.navigateToBookingHistory()
    }

    override fun onItemClicked(booking: Booking) {

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

}
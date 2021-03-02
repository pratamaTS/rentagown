package com.example.rentagown.v2.ui.mybookingshistory

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.source.BookingDataSource

class MyBookingsHistoryPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<MyBookingsHistoryConstract.View>(), MyBookingsHistoryConstract.Presenter {

    override fun start() {
        super.start()

        loadMyBookingsHistory()
    }

    override fun loadMyBookingsHistory() {
        view?.showLoadingContent(true)

        safeCallPaging(repository.getMyBookingsHistory(), object : Listener<List<Booking>> {
            override fun onSuccess(data: List<Booking>?) {
                val mData = data ?: listOf()

                view?.showEmptyPlaceHolder(mData.isEmpty())
                view?.showMyBookingsHistory(mData)
            }

        }, RequestConfiguration(updateLoadingIndicator = false))
    }

    override fun onBtnBrowseClicked() {
        view?.navigateToBrowseProducts()
    }

    override fun onBookingHistoryItemClicked(booking: Booking) {
        view?.navigateToBookingHistoryDetail(booking)
    }

    override fun onBookingDataChanged(booking: Booking?) {
        booking?.apply { view?.setDataBookingChanged(this) }
    }


}
package com.example.rentagown.v2.ui.mybookingshistory

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

class MyBookingsHistoryConstract {

    interface View : BaseView<Presenter> {

        fun showMyBookingsHistory(bookings: List<Booking>)
        fun navigateToBrowseProducts()
        fun navigateToBookingHistoryDetail(booking: Booking)
        fun setDataBookingChanged(booking: Booking)

    }

    interface Presenter : BasePresenter {

        fun loadMyBookingsHistory()
        fun onBtnBrowseClicked()
        fun onBookingHistoryItemClicked(booking: Booking)
        fun onBookingDataChanged(booking: Booking?)

    }

}
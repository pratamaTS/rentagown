package com.example.rentagown.v2.ui.home.mybookings

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.ui.home.mybookings.item.MyBookingItem

class MyBookingsContract {

    interface View : BaseView<Presenter> {

        fun showMyBookings(bookings: List<Booking>)
        fun navigateToBrowse()
        fun navigateToBookingHistory()
        fun navigateToFitting(transactionId: String, fittingId: String?)
        fun updateFittingData(transactionId: String, fittingId: String?)

    }

    interface Presenter : BasePresenter {

        fun loadMyBookings()
        fun onBtnBrowserClicked()
        fun onBtnBookingHistoryClicked()
        fun onItemClicked(booking: Booking)
        fun onBtnFittingClicked(booking: Booking)
        fun onFittingSaved(transactionId: String?, fittingId: String?)

    }


}
package com.example.rentagown.v2.ui.mybookinghistory

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

class MyBookingHistoryConstract {

    interface View : BaseView<Presenter> {

        fun showMyBookingsHistory(bookings: List<Booking>)
        fun navigateToBrowseProducts()

    }

    interface Presenter : BasePresenter {

        fun loadMyBookingsHistory()
        fun onBtnBrowseClicked()

    }

}
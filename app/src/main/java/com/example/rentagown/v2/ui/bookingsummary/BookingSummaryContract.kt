package com.example.rentagown.v2.ui.bookingsummary

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

interface BookingSummaryContract {

    interface View: BaseView<Presenter> {

        fun navigateToConfirmPayment(booking: Booking)
        fun setDataBookingToView(booking: Booking)
        fun getBookingData(): Booking?

        fun showMsgBookingNotFound()
        fun navigateToBookingSuccess()

    }

    interface Presenter: BasePresenter {

        fun onBtnConfirmPaymentClicked()
        fun onBtnBackToHomeClicked()
        fun onPaymentConfirmed(booking: Booking?)

    }

}
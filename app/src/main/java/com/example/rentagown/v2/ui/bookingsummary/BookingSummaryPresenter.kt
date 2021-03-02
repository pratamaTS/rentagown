package com.example.rentagown.v2.ui.bookingsummary

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Booking

class BookingSummaryPresenter : BaseRAGPresenter<BookingSummaryContract.View>(), BookingSummaryContract.Presenter {


    override fun start() {
        super.start()

        val booking = view?.getBookingData()

        if(booking == null) {
            view?.showMsgBookingNotFound()
            return
        }

        view?.setDataBookingToView(booking)
    }

    override fun onBtnConfirmPaymentClicked() {
        view?.getBookingData()?.let { booking ->
            view?.navigateToConfirmPayment(booking)
        }
    }

    override fun onBtnBackToHomeClicked() {
        view?.closeView()
    }

    override fun onPaymentConfirmed(booking: Booking?) {
        view?.navigateToBookingSuccess()
    }

}

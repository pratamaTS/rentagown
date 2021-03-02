package com.example.rentagown.v2.ui.bookingsummary

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.source.BookingDataSource

class BookingSummaryPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<BookingSummaryContract.View>(), BookingSummaryContract.Presenter {

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

    override fun onBtnCancelTransactionClicked() {
        view?.getBookingData()?.transactionId?.let { transactionId ->
            view?.showLoading(true)

            safeCall(repository.cancelBooking(transactionId), object : Listener<Booking> {
                override fun onSuccess(data: Booking?) {
                    view?.showMsgSuccessCancelBooking()
                    view?.closeView()
                }
            })
        }
    }

    override fun onPaymentConfirmed(booking: Booking?) {
        view?.navigateToBookingSuccess()
    }

}

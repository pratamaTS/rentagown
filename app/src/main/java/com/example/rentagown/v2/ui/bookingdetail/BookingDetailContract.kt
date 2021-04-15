package com.example.rentagown.v2.ui.bookingdetail

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

class BookingDetailContract {

    interface View : BaseView<Presenter> {

        fun getTransactionId(): String?
        fun getBookingData(): Booking?
        fun setDataBookingToView(booking: Booking)
        fun navigateToConfirmPayment(booking: Booking)
        fun navigateToReviewBooking(booking: Booking)
        fun navigateToFitting(transactionId: String?, fittingId: String?)
        fun showMsgSuccessCancelBooking()
        fun setResultBookingChanged(booking: Booking, finish: Boolean = true)

    }

    interface Presenter : BasePresenter {

        fun onBtnPayClicked()
        fun onBtnActionClicked()

        fun onFittingSaved(transactionId: String?, fittingId: String?)
        fun onPaymentConfirmationSaved(booking: Booking?)
        fun onBookingReviewed(transactionId: String?, ratingId: String?)

    }

}
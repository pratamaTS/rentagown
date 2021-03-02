package com.example.rentagown.v2.ui.bookingdetail

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.BookingStatusEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.source.BookingDataSource

class BookingDetailPresenter(private val bookingDataSource: BookingDataSource) : BaseRAGPresenter<BookingDetailContract.View>(), BookingDetailContract.Presenter {

    override fun onBtnPayClicked() {
        view?.getBookingData()?.let { booking ->
            view?.navigateToConfirmPayment(booking)
        }
    }

    override fun onBtnActionClicked() {
        view?.getBookingData()?.apply {

            if(BookingStatusEnum.isOnGoing(status)) {
                // pay confirmation
                view?.navigateToFitting(transactionId, fittingId)
            } else if(BookingStatusEnum.isCompleted(status) && ratingId.isNullOrBlank()) {
                // rate
                view?.navigateToReviewBooking(this)
            }

        }
    }

    override fun onFittingSaved(transactionId: String?, fittingId: String?) {
        view?.getBookingData()?.apply {
            view?.setDataBookingToView(this)
            view?.setResultBookingChanged(copy(
                fittingId = fittingId
            ), false)
        }
    }

    override fun onPaymentConfirmationSaved(booking: Booking?) {
        booking?.apply {
            view?.setDataBookingToView(this)
            view?.setResultBookingChanged(this, false)
        }
    }

    override fun onBookingReviewed(transactionId: String?, ratingId: String?) {
        view?.getBookingData()?.let { mBooking ->
            val newBookingData = mBooking.copy(ratingId = ratingId)

            view?.setDataBookingToView(newBookingData)
            view?.setResultBookingChanged(newBookingData, false)
        }
    }

    override fun start() {
        super.start()

        val booking = view?.getBookingData()

        booking?.let { mBooking ->
            view?.setDataBookingToView(mBooking)
        }

    }

}
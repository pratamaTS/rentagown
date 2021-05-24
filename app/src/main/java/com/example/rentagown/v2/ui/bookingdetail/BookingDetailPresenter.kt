package com.example.rentagown.v2.ui.bookingdetail

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.BookingStatusEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.ReqCancelBooking
import com.example.rentagown.v2.data.source.BookingDataSource

class BookingDetailPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<BookingDetailContract.View>(), BookingDetailContract.Presenter {

    override fun onBtnPayClicked() {
        view?.getBookingData()?.let { booking ->
            view?.navigateToConfirmPayment(booking)
        }
    }

    override fun onBtnActionClicked() {
        view?.getBookingData()?.apply {

            if(BookingStatusEnum.isAbleToFitting(ableToFitting)) {
                // pay confirmation
                view?.navigateToFitting(transactionId, fittingId)
            } else if(BookingStatusEnum.isCompleted(status) && ratingId.isNullOrBlank()) {
                // rate
                view?.navigateToReviewBooking(this)
            } else if(status == 1){
                view?.getBookingData()?.transactionId?.let { transactionId ->
                    view?.showLoading(true)

                    safeCall(repository.cancelBooking(ReqCancelBooking(transactionId)), object : Listener<Booking> {
                        override fun onSuccess(data: Booking?) {
                            view?.showMsgSuccessCancelBooking()
                            view?.setResultBookingChanged(data!!)
                        }
                    })
                }
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
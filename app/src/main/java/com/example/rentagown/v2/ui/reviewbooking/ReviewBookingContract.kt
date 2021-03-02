package com.example.rentagown.v2.ui.reviewbooking

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

class ReviewBookingContract {

    interface View : BaseView<Presenter> {

        fun getBookingData(): Booking?
        fun getReviewStart(): Float
        fun getReviewComment(): String
        fun setDataBookingToView(booking: Booking)
        fun showMsgSuccessReviewBooking()
        fun setResultBookingReviewed(transactionId: String?, ratingId: String?)

    }

    interface Presenter : BasePresenter {

        fun onBtnSubmitReviewClicked()

    }

}
package com.example.rentagown.v2.ui.reviewbooking

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.model.ReqReviewBooking
import com.example.rentagown.v2.data.source.BookingDataSource

class ReviewBookingPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<ReviewBookingContract.View>(), ReviewBookingContract.Presenter {


    override fun start() {
        super.start()

        view?.getBookingData()?.apply {
            view?.setDataBookingToView(this)
        }
    }

    override fun onBtnSubmitReviewClicked() {
        val rating = view?.getReviewStart() ?: 0f
        val comment = view?.getReviewComment()

        view?.getBookingData()?.let { mBooking ->

            val reqReviewBooking = ReqReviewBooking(
                transactionId = mBooking.transactionId,
                productId = mBooking.productId,
                reviewStar = rating.toInt(),
                reviewComment = comment
            )

            view?.showLoading(true)
            safeCall(repository.reviewBooking(reqReviewBooking), object : Listener<ProductReview> {
                override fun onSuccess(data: ProductReview?) {
                    data?.let { mData ->
                        view?.showMsgSuccessReviewBooking()
                        view?.setResultBookingReviewed(mData.transactionId, mData.ratingId)
                    }
                }
            }, RequestConfiguration(updateLoadingContentIndicator = false))

        }
    }

}
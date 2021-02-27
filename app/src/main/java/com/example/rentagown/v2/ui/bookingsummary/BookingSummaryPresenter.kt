package com.example.rentagown.v2.ui.bookingsummary

import com.example.rentagown.v2.base.BaseRAGPresenter

class BookingSummaryPresenter : BaseRAGPresenter<BookingSummaryContract.View>(), BookingSummaryContract.Presenter {

    override fun onBtnConfirmPaymentClicked() {
        view?.navigateToConfirmPayment()
    }


}

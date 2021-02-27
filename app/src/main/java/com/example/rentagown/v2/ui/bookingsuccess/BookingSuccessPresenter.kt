package com.example.rentagown.v2.ui.bookingsuccess

import com.example.rentagown.v2.base.BaseRAGPresenter

class BookingSuccessPresenter : BaseRAGPresenter<BookingSuccessContract.View>(), BookingSuccessContract.Presenter {

    override fun onBtnBackToHomeClicked() {
        view?.navigateBackToHome()
    }

}
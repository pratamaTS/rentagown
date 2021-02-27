package com.example.rentagown.v2.ui.confirmpayment

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.source.BookingDataSource

class ConfirmPaymentPresenter() : BaseRAGPresenter<ConfirmPaymentContract.View>(), ConfirmPaymentContract.Presenter {

    override fun onBtnConfirmClicked() {
        view?.navigateToBookingSuccess()
    }

}
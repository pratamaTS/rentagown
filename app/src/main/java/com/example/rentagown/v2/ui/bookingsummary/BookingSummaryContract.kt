package com.example.rentagown.v2.ui.bookingsummary

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView

interface BookingSummaryContract {

    interface View: BaseView<Presenter> {

        fun book()
        fun navigateToConfirmPayment()

    }

    interface Presenter: BasePresenter {

        fun onBtnConfirmPaymentClicked()

    }

}
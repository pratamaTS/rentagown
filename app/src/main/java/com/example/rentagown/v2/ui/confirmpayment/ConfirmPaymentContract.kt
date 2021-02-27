package com.example.rentagown.v2.ui.confirmpayment

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView

class ConfirmPaymentContract {

    interface View : BaseView<Presenter> {

        fun navigateToBookingSuccess()

    }

    interface Presenter : BasePresenter {

        fun onBtnConfirmClicked()

    }


}
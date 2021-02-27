package com.example.rentagown.v2.ui.bookingsuccess

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView

class BookingSuccessContract {

    interface View : BaseView<Presenter> {
        fun navigateBackToHome()
    }

    interface Presenter : BasePresenter {

        fun onBtnBackToHomeClicked()

    }

}
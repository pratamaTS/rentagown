package com.example.rentagown.v2.ui.confirmpayment

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking

class ConfirmPaymentContract {

    interface View : BaseView<Presenter> {

        fun navigateToBookingSuccess()
        fun getBookingData(): Booking?
        fun setPaymentValue(paymentValueStr: String?)
        fun getBankName(): String
        fun getAccountNumber(): String
        fun getAccountName(): String
        fun getPaymentAmount(): String
        fun setResultPaymentSuccess(booking: Booking?)

        fun showMsgBookingNotFound()
        fun showMsgBankEmpty()
        fun showMsgAccountNumberEmpty()
        fun showMsgAccountNameEmpty()
        fun showMsgPaymentAmountEmpty()

    }

    interface Presenter : BasePresenter {

        fun onBtnConfirmClicked()

    }


}
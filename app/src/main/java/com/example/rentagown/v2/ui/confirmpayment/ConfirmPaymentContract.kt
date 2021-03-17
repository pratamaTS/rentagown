package com.example.rentagown.v2.ui.confirmpayment

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.MasterBank

class ConfirmPaymentContract {

    interface View : BaseView<Presenter> {

        fun navigateToBookingSuccess()
        fun getBookingData(): Booking?
        fun setSpinnerBankItem(bank: List<MasterBank>)
        fun setPaymentValue(paymentValueStr: String?)
        fun getInvoice(): String
        fun getBankID(): String
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

        fun loadBank()
        fun onBtnConfirmClicked()

    }


}
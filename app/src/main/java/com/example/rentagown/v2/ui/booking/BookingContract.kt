package com.example.rentagown.v2.ui.booking

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.*

class BookingContract {

    interface View : BaseView<Presenter> {

        fun navigateToChangeAddress(selectedAddress: Address?)
        fun setAddressDataToView(address: Address)
        fun removeAddressDataFromView()
        fun getSelectedAddressData(): Address?

        fun navigateToChangeBank(selectedBank: Bank?)
        fun setBankDataToView(bank: Bank)
        fun removeBankDataFromView()
        fun getSelectedBankData(): Bank?

        fun navigateToWhatsapp(phoneNumber: String)
        fun getReqCreateBookingData(): ReqCreateBooking?

        fun navigateToChoosePaymentType(paymentType: PaymentTypeEnum?)
        fun getSelectedPaymentType(): PaymentTypeEnum?
        fun setPaymentTypeDataToView(paymentType: PaymentTypeEnum, reqCreateBooking: ReqCreateBooking?, product: Product?)
        fun removePaymentTypeDataFromView(reqCreateBooking: ReqCreateBooking?, product: Product?)

        fun getSelectedProduct(): Product?

        fun setResultBookingCreated()
        fun navigateToBookingSummary(booking: Booking)

        fun showMsgAddressNotSelected()
        fun showMsgPaymentTypeNotSelected()
        fun showMsgPaymentMethodNotSelected()
        fun showMsgProductNotFound()
        fun showMsgBookingDataNotFound()

    }

    interface Presenter : BasePresenter {

        fun onDefaultAddressLoaded()
        fun onBtnChangeAddressClicked()
        fun onBtnAddAddressClicked()
        fun onAddressSelected(address: Address?, default: Boolean)
        fun onAddressEdited(address: Address?)
        fun onAddressDeleted(address: Address?)

        fun onBtnChangeBankClicked()
        fun onBtnAddBankClicked()
        fun onBankSelected(bank: Bank?)

        fun onBtnWhatsappClicked()
        fun onBtnPayClicked()

        fun onBtnChoosePaymentTypeClicked()
        fun onSelectedPaymentType(paymentType: PaymentTypeEnum?)

    }

}
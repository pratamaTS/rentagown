package com.example.rentagown.v2.ui.booking

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.data.source.BookingDataSource

class BookingPresenter(private val createBooking: ReqCreateBooking?,
                       private val repository: BookingDataSource) : BaseRAGPresenter<BookingContract.View>(), BookingContract.Presenter {


    override fun start() {
        super.start()

        view?.setPaymentTypeDataToView(PaymentTypeEnum.getDefaultPaymentType(), view?.getReqCreateBookingData(), view?.getSelectedProduct())
    }


    override fun onBtnChangeAddressClicked() {
        view?.navigateToChangeAddress(view?.getSelectedAddressData())
    }

    override fun onBtnAddAddressClicked() {
        view?.navigateToChangeAddress(view?.getSelectedAddressData())
    }

    override fun onAddressSelected(address: Address?) {
        address?.let {
            view?.setAddressDataToView(it)
        }
    }

    override fun onAddressEdited(address: Address?) {
        address?.let { mAddress ->
            val selectedAddress = view?.getSelectedAddressData()
            if(selectedAddress?.addressId == mAddress.addressId) {
                view?.setAddressDataToView(mAddress)
            }
        }
    }

    override fun onAddressDeleted(address: Address?) {
        val selectedAddress = view?.getSelectedAddressData()
        if(selectedAddress?.addressId == address?.addressId) {
            view?.removeAddressDataFromView()
        }
    }

    override fun onBtnChangeBankClicked() {
        view?.navigateToChangeBank(view?.getSelectedBankData())
    }

    override fun onBtnAddBankClicked() {
        view?.navigateToChangeBank(view?.getSelectedBankData())
    }

    override fun onBankSelected(bank: Bank?) {
        bank?.let {
            view?.setBankDataToView(it)
        }
    }

    override fun onBtnWhatsappClicked() {
        val number = "+6281806155676"
        view?.navigateToWhatsapp(number)
    }

    override fun onBtnPayClicked() {
        // validate input
        val address = view?.getSelectedAddressData()
        val paymentType = view?.getSelectedPaymentType()
        val paymentMethod = view?.getSelectedBankData()
        val product = view?.getSelectedProduct()

        val reqCreateBooking = view?.getReqCreateBookingData()

        if(address == null) {
            view?.showMsgAddressNotSelected()
            return
        }

        if(paymentType == null) {
            view?.showMsgPaymentTypeNotSelected()
            return
        }

        if(paymentMethod == null) {
            view?.showMsgPaymentMethodNotSelected()
            return
        }

        if(product == null) {
            view?.showMsgProductNotFound()
            return
        }

        if(reqCreateBooking == null) {
            view?.showMsgBookingDataNotFound()
            return
        }

        view?.showLoading(true)

        val mReqCreateBooking = reqCreateBooking.copy(
            addressId = address.addressId,
            paymentMethod = paymentType.typeId,
            bankId = paymentMethod.bankId,
            phoneNumber = address.receiverPhoneNumber
        )

        safeCall(repository.createBooking(mReqCreateBooking), object : Listener<Booking> {
            override fun onSuccess(data: Booking?) {
                data?.let {
                    view?.setResultBookingCreated()
                    view?.navigateToBookingSummary(data)
                }
            }

            override fun onFailed(message: String?) {
                super.onFailed(message)

                view?.setResultBookingCreated()
                view?.navigateToBookingSummary(Booking())
            }
        }, RequestConfiguration(updateLoadingContentIndicator = false))

    }

    override fun onBtnChoosePaymentTypeClicked() {
        view?.navigateToChoosePaymentType(view?.getSelectedPaymentType())
    }

    override fun onSelectedPaymentType(paymentType: PaymentTypeEnum?) {
        paymentType?.let {
            view?.setPaymentTypeDataToView(it, view?.getReqCreateBookingData(), view?.getSelectedProduct())
        }
    }

}
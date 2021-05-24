package com.example.rentagown.v2.ui.booking

import android.util.Log
import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.*
import com.example.rentagown.v2.data.source.BankDataSource
import com.example.rentagown.v2.data.source.BookingDataSource

class BookingPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<BookingContract.View>(), BookingContract.Presenter {


    override fun start() {
        super.start()

        onDefaultAddressLoaded()

        onDefaultBankLoaded()

        view?.setPaymentTypeDataToView(PaymentTypeEnum.getDefaultPaymentType(), view?.getReqCreateBookingData(), view?.getSelectedProduct())
    }

    override fun onDefaultAddressLoaded() {
        view?.showLoading(true)

        safeCall(repository.getDefaultAddress(), object : Listener<Address> {
            override fun onSuccess(data: Address?) {
                data?.let {address ->
                    view?.setAddressDataToView(address)
                }
            }
        }, RequestConfiguration(updateLoadingIndicator = false, updateLoadingContentIndicator = false))
    }

    override fun onDefaultBankLoaded() {
        view?.showLoading(true)

        safeCall(repository.getDefaultBank(), object : Listener<Bank> {
            override fun onSuccess(data: Bank?) {
                data?.let {bank ->
                    view?.setBankDataToView(bank, false)
                }
            }
        }, RequestConfiguration(updateLoadingIndicator = false, updateLoadingContentIndicator = false))
    }

    override fun onBtnChangeAddressClicked() {
        view?.navigateToChangeAddress(view?.getSelectedAddressData())
    }

    override fun onBtnAddAddressClicked() {
        view?.navigateToChangeAddress(view?.getSelectedAddressData())
    }

    override fun onAddressSelected(address: Address?, default: Boolean) {
        address?.let {
            val mReqSetAddress = ReqSetAddress(
                    addressId = it.addressId
                )

            when(default) {
                true -> {
                    view?.showLoading(true)
                    safeCall (repository.setDefaultAddress(mReqSetAddress), object :
                        Listener<Address> {
                        override fun onSuccess(data: Address?) {
                            data?.let {
                                view?.setAddressDataToView(it)
                            }
                        }
                    }, RequestConfiguration(updateLoadingContentIndicator = false))
                }
                false -> view?.setAddressDataToView(it)

            }
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
            view?.setBankDataToView(it, true)
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
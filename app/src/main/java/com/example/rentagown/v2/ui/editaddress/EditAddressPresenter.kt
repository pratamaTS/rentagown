package com.example.rentagown.v2.ui.editaddress

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.source.AddressDataSource

class EditAddressPresenter(private val repository: AddressDataSource) :
    BaseRAGPresenter<EditAddressContract.View>(), EditAddressContract.Presenter {

    override fun onStart(address: Address?) {
        if(address == null) {
            // adding
            view?.setTitleAdding()
            view?.setAddressData(Address()) // to clear input
        } else {
            // editing
            view?.setTitleEditing()
            view?.setAddressData(address)
        }
    }

    override fun onBtnSaveClicked() {
        // blm ada validasi untuk sekarang biar cepet
        view?.getCurrentAddressData()?.let { address ->
            view?.showLoading(true)
            if(!address.addressId.isNullOrBlank()) {
                // edit
                safeCall(repository.updateAddress(address.addressId, address), object : Listener<Address> {
                    override fun onSuccess(data: Address?) {
                        view?.showLoading(false)
                        view?.successEditAddress(data)
                    }

                    override fun onFailed(message: String?) {
                        view?.showLoading(false)
                        super.onFailed(message)
                    }
                })
            } else {
                // add
                if(address.addressLabel!!.isNotEmpty() && address.address!!.isNotEmpty() && address.receiverName!!.isNotEmpty() && address.receiverPhoneNumber!!.isNotEmpty()) {
                    safeCall(repository.addAddress(address), object : Listener<Address> {
                        override fun onSuccess(data: Address?) {
                            view?.showLoading(false)
                            view?.successAddAddress(data)
                        }

                        override fun onFailed(message: String?) {
                            view?.showLoading(false)
                            super.onFailed(message)
                        }
                    })
                }else if(address.addressLabel.isNullOrEmpty()){
                    view?.showLoading(false)
                    view?.showErrorMessage("Address label is still empty")
                }else if(address.address.isNullOrEmpty()){
                    view?.showLoading(false)
                    view?.showErrorMessage("Address is still empty")
                }else if(address.receiverName.isNullOrEmpty()){
                    view?.showLoading(false)
                    view?.showErrorMessage("Name is still empty")
                }else {
                    view?.showLoading(false)
                    view?.showErrorMessage("Phone number is still empty")
                }
            }
        }
    }

}
package com.example.rentagown.v2.ui.chooseaddress

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.source.AddressDataSource

class ChooseAddressPresenter(private val repository: AddressDataSource) :
    BaseRAGPresenter<ChooseAddressContract.View>(), ChooseAddressContract.Presenter {

    override fun loadAddressList() {

        view?.showLoadingContent(true)
        safeCallPaging(repository.getAddressList(), object: Listener<List<Address>>{
            override fun onSuccess(data: List<Address>?) {
                view?.showAddressList(data ?: listOf())
            }
        })
    }

    override fun onBtnAddAddressClicked() {
        view?.navigateToAddAddress()
    }

    override fun onBtnChooseAddressClicked(address: Address) {
        view?.chooseAddress(address)
    }

    override fun onBtnEditAddressClicked(address: Address) {
        view?.navigateToEditAddress(address)
    }

    override fun onBtnDeleteAddressClicked(address: Address) {
        view?.showLoading(true)
        address.addressId?.let { addressId ->
            safeCall(repository.deleteAddress(addressId), object : Listener<Address> {
                override fun onSuccess(data: Address?) {
                    view?.showLoading(false)
                    view?.removeAddress(addressId)

                    // check if address is the same with current selected address
                    data?.let { address ->
                        if(address.addressId == view?.getSelectedAddressId()) {
                            view?.setResultAddressDeleted(address)
                        }
                    }
                }

                override fun onFailed(message: String?) {
                    view?.showLoading(false)
                }
            })
        }
    }

    override fun onAddressEdited(address: Address?) {
        if(address != null) {
            view?.editAddress(address)

            // set result address is editted if the id is the same with current selected address
            val selectedAddressId = view?.getSelectedAddressId()

            if(selectedAddressId == address.addressId) {
                view?.setResultAddressEdited(address)
            }
        } else {
            loadAddressList()
        }
    }

    override fun onAddressAdded(address: Address?) {
        if(address != null) {
            view?.addAddress(address)
        } else {
            loadAddressList()
        }
    }

}
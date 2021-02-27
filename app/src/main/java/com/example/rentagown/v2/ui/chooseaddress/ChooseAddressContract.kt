package com.example.rentagown.v2.ui.chooseaddress

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Address

class ChooseAddressContract {

    interface View: BaseView<Presenter> {

        fun showMessageSuccessChangeAddress()
        fun showAddressList(addressList: List<Address>)
        fun chooseAddress(address: Address)
        fun navigateToEditAddress(address: Address)
        fun navigateToAddAddress()
        fun removeAddress(addressId: String)
        fun editAddress(address: Address)
        fun addAddress(address: Address)
        fun getSelectedAddressId(): String?
        fun setResultAddressEdited(address: Address)
        fun setResultAddressDeleted(address: Address)

    }

    interface Presenter: BasePresenter {

        fun loadAddressList()
        fun onBtnAddAddressClicked()
        fun onBtnChooseAddressClicked(address: Address)
        fun onBtnEditAddressClicked(address: Address)
        fun onBtnDeleteAddressClicked(address: Address)
        fun onAddressEdited(address: Address?)
        fun onAddressAdded(address: Address?)

    }


}
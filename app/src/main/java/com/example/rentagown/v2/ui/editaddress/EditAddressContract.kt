package com.example.rentagown.v2.ui.editaddress

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Address

class EditAddressContract {

    interface View: BaseView<Presenter> {

        fun setTitleEditing()
        fun setTitleAdding()
        fun setAddressData(address: Address)
        fun getCurrentAddressData(): Address
        fun successAddAddress(address: Address?)
        fun successEditAddress(address: Address?)

    }

    interface Presenter: BasePresenter {

        fun onStart(address: Address?)
        fun onBtnSaveClicked()

    }

}
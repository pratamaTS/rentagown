package com.example.rentagown.Connection.Interface

import com.example.rentagown.v2.data.model.Address

interface GetDefaultAddressInterface {
    fun onSuccessGetDefaultAddress(address: Address)
    fun onErrorGetDefaultAddress(msg: String)
}
package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.CreateAddress.DataAddAddress
import com.example.rentagown.v2.data.model.Address

interface SetDefaultAddressInterface {
    fun onSuccessSetDefaultAddress(address: Address)
    fun onErrorSetDefaultAddress(msg: String)
}
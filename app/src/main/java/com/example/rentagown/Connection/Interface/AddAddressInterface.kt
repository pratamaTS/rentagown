package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.CreateAddress.DataAddAddress

interface AddAddressInterface {
    fun onSuccessAddAddress(dataAddAddress: DataAddAddress)
    fun onErrorAddAddress(msg: String)
}
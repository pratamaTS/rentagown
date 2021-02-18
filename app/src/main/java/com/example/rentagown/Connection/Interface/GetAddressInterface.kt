package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.GetAddress.DataAddress

interface GetAddressInterface {
    fun onSuccessGetAddress(dataAddress: ArrayList<DataAddress>?)
    fun onErrorGetAddress(msg: String)
}
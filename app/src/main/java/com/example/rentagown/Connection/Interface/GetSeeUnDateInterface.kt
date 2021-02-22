package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.GetAddress.DataAddress
import com.example.rentagown.Response.SeeUnDate.DataSeeUnDate

interface GetSeeUnDateInterface {
    fun onSuccessGetSeeUnDate(dataSeeUnDate: ArrayList<DataSeeUnDate>?)
    fun onErrorGetSeeUnDate(msg: String)
}
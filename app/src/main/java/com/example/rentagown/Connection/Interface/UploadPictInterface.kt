package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.EditProfile.DataPict

interface UploadPictInterface {
    fun onSuccessUploadPict(dataPict: ArrayList<DataPict>)
    fun onErrorGetUploadPict(msg: String)
}
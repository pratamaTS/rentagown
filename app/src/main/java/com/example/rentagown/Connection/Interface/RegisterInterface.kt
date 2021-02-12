package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Login.DataLogin

interface RegisterInterface {
    fun onSuccessGetRegister()
    fun onErrorGetRegister(msg: String)
}
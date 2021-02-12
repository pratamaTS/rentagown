package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Login.DataLogin

interface LoginInterface {
    fun onSuccessGetLogin()
    fun onErrorGetLogin(msg: String)
}
package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.ChangePassword.DataChangePassword

interface ChangePasswordInterface {
    fun onSuccessChangePassword(dataChangePassword: DataChangePassword)
    fun onErrorChangePassword(msg: String)
}
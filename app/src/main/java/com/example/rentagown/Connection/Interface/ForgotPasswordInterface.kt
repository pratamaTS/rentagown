package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.ForgotPassword.ResponseForgotPassword

interface ForgotPasswordInterface {
    fun onSuccessForgotPassword(responseForgotPassword: ResponseForgotPassword)
    fun onErrorForgotPassword(msg: String)
}
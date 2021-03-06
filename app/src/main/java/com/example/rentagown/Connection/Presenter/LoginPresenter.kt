 package com.example.rentagown.Connection.Presenter

import android.content.Context
import android.util.Log
import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Connection.Interface.LoginInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.Promo.ResponsePromo
import com.example.rentagown.v2.util.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val loginInterface: LoginInterface) {
    private lateinit var sessionManager: SessionManager

    fun login(context: Context, loginBody: LoginBody){

        sessionManager = SessionManager(context)

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfig.service()
                .login(map, loginBody)
                .enqueue(object : Callback<ResponseLogin> {

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        loginInterface.onErrorGetLogin(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                        if(response.isSuccessful){
                            val body = response.body()
                            sessionManager.saveAuthToken(body?.data?.accessToken.toString())
                            loginInterface.onSuccessGetLogin()
                        }else{
                            val error: ResponseError? = ErrorUtils.parseError(response, context)
                            // … and use it to show error information

                            // … or just log the issue like we’re doing :)
                            // … and use it to show error information

                            // … or just log the issue like we’re doing :)
                            Log.d("error message", error?.error.toString())
                            loginInterface.onErrorGetLogin(error?.error.toString())
                        }
                    }

                })
    }
}
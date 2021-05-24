 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Body.RegisterBody
import com.example.rentagown.Connection.Interface.LoginInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.Interface.RegisterInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.Promo.ResponsePromo
import com.example.rentagown.Response.Register.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(val registerInterface: RegisterInterface) {

    fun register(registerBody: RegisterBody){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfig.service()
                .register(map, registerBody)
                .enqueue(object : Callback<ResponseRegister> {

                    override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                        registerInterface.onErrorGetRegister(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                        if(response.isSuccessful){
                            val body = response.body()
                            registerInterface.onSuccessGetRegister()
                        }else{
                            val error = response.errorBody().toString()
                            registerInterface.onErrorGetRegister(error)
                        }
                    }

                })
    }
}
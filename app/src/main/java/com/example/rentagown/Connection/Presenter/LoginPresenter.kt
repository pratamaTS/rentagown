 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Connection.Interface.LoginInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val loginInterface: LoginInterface) {
    fun login(loginBody: LoginBody){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfig.service()
                .login(map, loginBody)
                .enqueue(object : Callback<ResponseLogin> {

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        loginInterface.onErrorGetLogin(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            loginInterface.onSuccessGetLogin(data)
                        }else{
                            val error = response.errorBody().toString()
                            loginInterface.onErrorGetLogin(error)
                        }
                    }

                })
    }
}
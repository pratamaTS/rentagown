 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.Login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAddressPresenter(val addAddressInterface: AddAddressInterface) {
    private lateinit var sessionManager: SessionManager

    fun addAddress(context: Context, addAddressBody: AddAddressBody){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .addAddress(map, addAddressBody)
                .enqueue(object : Callback<ResponseAddAddress> {

                    override fun onFailure(call: Call<ResponseAddAddress>, t: Throwable) {
                        addAddressInterface.onErrorAddAddress(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseAddAddress>, response: Response<ResponseAddAddress>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            addAddressInterface.onSuccessAddAddress(data!!)
                        }else{
                            val error = response.errorBody().toString()
                            addAddressInterface.onErrorAddAddress(error)
                        }
                    }

                })
    }
}
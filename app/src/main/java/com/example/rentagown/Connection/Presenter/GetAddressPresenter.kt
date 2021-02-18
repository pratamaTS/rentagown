 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAddressPresenter(val addressInterface: GetAddressInterface) {
    fun getAllAddress(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getAddress(map)
                .enqueue(object : Callback<ResponseGetAddress> {

                    override fun onFailure(call: Call<ResponseGetAddress>, t: Throwable) {
                        addressInterface.onErrorGetAddress(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseGetAddress>, response: Response<ResponseGetAddress>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            addressInterface.onSuccessGetAddress(data)
                        }else{
                            val error = response.errorBody().toString()
                            addressInterface.onErrorGetAddress(error)
                        }
                    }

                })
    }
}
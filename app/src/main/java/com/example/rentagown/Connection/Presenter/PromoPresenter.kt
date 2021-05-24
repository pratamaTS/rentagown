 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoPresenter(val promoInterface: PromoInterface) {
    fun getAllPromo(){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfig.service()
                .getAllPromo(map)
                .enqueue(object : Callback<ResponsePromo> {

                    override fun onFailure(call: Call<ResponsePromo>, t: Throwable) {
                        promoInterface.onErrorGetPromo(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponsePromo>, response: Response<ResponsePromo>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            promoInterface.onSuccessGetPromo(data)
                        }else{
                            val error = response.errorBody().toString()
                            promoInterface.onErrorGetPromo(error)
                        }
                    }

                })
    }
}
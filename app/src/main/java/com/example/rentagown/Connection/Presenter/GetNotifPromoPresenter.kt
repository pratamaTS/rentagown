 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.*
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.GetWishlist.ResponseGetWishlist
import com.example.rentagown.Response.Notification.ResponseNotifPromo
import com.example.rentagown.Response.Notification.ResponseNotification
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNotifPromoPresenter(val getNotifPromoInterface: GetNotifPromoInterface) {
    fun getAllNotifPromo(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getNotifPromo(map)
                .enqueue(object : Callback<ResponseNotifPromo> {

                    override fun onFailure(call: Call<ResponseNotifPromo>, t: Throwable) {
                        getNotifPromoInterface.onErrorGetNotifPromo(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseNotifPromo>, response: Response<ResponseNotifPromo>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            getNotifPromoInterface.onSuccessGetNotifPromo(data)
                        }else{
                            val error = response.errorBody().toString()
                            getNotifPromoInterface.onErrorGetNotifPromo(error)
                        }
                    }

                })
    }
}
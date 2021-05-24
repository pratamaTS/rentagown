 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Interface.GetNotificationInterface
import com.example.rentagown.Connection.Interface.GetWishlistInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.GetWishlist.ResponseGetWishlist
import com.example.rentagown.Response.Notification.ResponseNotification
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNotificationPresenter(val getNotificationInterface: GetNotificationInterface) {
    fun getAllNotification(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getNotification(map)
                .enqueue(object : Callback<ResponseNotification> {

                    override fun onFailure(call: Call<ResponseNotification>, t: Throwable) {
                        getNotificationInterface.onErrorGetNotification(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseNotification>, response: Response<ResponseNotification>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            getNotificationInterface.onSuccessGetNotification(data)
                        }else{
                            val error = response.errorBody().toString()
                            getNotificationInterface.onErrorGetNotification(error)
                        }
                    }

                })
    }
}
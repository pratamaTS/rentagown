 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Interface.GetSeeUnDateInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.Promo.ResponsePromo
import com.example.rentagown.Response.SeeUnDate.ResponseSeeUnDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetSeeUnDatePresenter(val seeUnDateInterface: GetSeeUnDateInterface) {
    fun getSeeUnDate(context: Context, idProduct: String){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //URL
        val url: String = "api/v/2/booking/date/" + idProduct

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getSeeUnDate(url, map)
                .enqueue(object : Callback<ResponseSeeUnDate> {

                    override fun onFailure(call: Call<ResponseSeeUnDate>, t: Throwable) {
                        seeUnDateInterface.onErrorGetSeeUnDate(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseSeeUnDate>, response: Response<ResponseSeeUnDate>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            seeUnDateInterface.onSuccessGetSeeUnDate(data)
                        }else{
                            val error = response.errorBody().toString()
                            seeUnDateInterface.onErrorGetSeeUnDate(error)
                        }
                    }

                })
    }
}
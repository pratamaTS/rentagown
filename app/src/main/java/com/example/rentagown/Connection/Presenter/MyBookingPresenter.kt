 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.MyBookingInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.MyBooking.ResponseMyBooking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBookingPresenter(val myBookingInterface: MyBookingInterface) {
    fun getMyBooking(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getMyBooking(map)
                .enqueue(object : Callback<ResponseMyBooking> {

                    override fun onFailure(call: Call<ResponseMyBooking>, t: Throwable) {
                        myBookingInterface.onErrorGetMyBooking(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseMyBooking>, response: Response<ResponseMyBooking>) {
                        if(response.isSuccessful){
                            val dataDetail = response.body()?.data
                            myBookingInterface.onSuccessGetMyBooking(dataDetail)
                        }else{
                            val error = response.errorBody().toString()
                            myBookingInterface.onErrorGetMyBooking(error)
                        }
                    }

                })
    }
}
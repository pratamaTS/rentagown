 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Body.CheckDateBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Interface.CheckDateInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.CheckDate.ResponseCheckDate
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.Login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckDatePresenter(val checkDateInterface: CheckDateInterface) {

    fun checkDate(context: Context, checkDateBody: CheckDateBody, idProduct: String){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //URL
        val url: String = "api/v/1/booking/checkdate/" + idProduct

        //Connect
        NetworkConfigAfterLogin.service(context)
                .checkDate(url, map, checkDateBody)
                .enqueue(object : Callback<ResponseCheckDate> {

                    override fun onFailure(call: Call<ResponseCheckDate>, t: Throwable) {
                        checkDateInterface.onErrorCheckDate(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseCheckDate>, response: Response<ResponseCheckDate>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            checkDateInterface.onSuccessCheckDate(data!!)
                        }else{
                            val error = response.errorBody().toString()
                            checkDateInterface.onErrorCheckDate(error)
                        }
                    }

                })
    }
}
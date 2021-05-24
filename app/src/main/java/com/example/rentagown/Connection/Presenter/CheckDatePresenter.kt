 package com.example.rentagown.Connection.Presenter

import android.content.Context
import android.util.Log
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Body.CheckDateBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Interface.CheckDateInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.Run
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.Response.CheckDate.ResponseCheckDate
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.v2.data.model.ReqCheckDate
import com.example.rentagown.v2.util.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckDatePresenter(val checkDateInterface: CheckDateInterface) {

    fun checkDate(context: Context, reqCheckDate: ReqCheckDate, idProduct: String){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //URL
        val url: String = "api/v/2/booking/checkdate/" + idProduct

        //Connect
        Run.after(1000, {
            NetworkConfigAfterLogin.service(context)
                .checkDate(url, map, reqCheckDate)
                .enqueue(object : Callback<ResponseCheckDate> {

                    override fun onFailure(call: Call<ResponseCheckDate>, t: Throwable) {
                        checkDateInterface.onErrorCheckDate(t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<ResponseCheckDate>,
                        response: Response<ResponseCheckDate>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            checkDateInterface.onSuccessCheckDate(data!!)
                        } else {
                            val error: ResponseError? = ErrorUtils.parseError(response, context)
                            // … and use it to show error information

                            // … or just log the issue like we’re doing :)
                            // … and use it to show error information

                            // … or just log the issue like we’re doing :)
                            Log.d("error message", error?.error.toString())
                            checkDateInterface.onErrorCheckDate(error?.error.toString())
                        }
                    }

                })
        })
    }
}
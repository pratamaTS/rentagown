 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Interface.GetBankInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.GetBank.ResponseGetBank
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetBankPresenter(val bankInterface: GetBankInterface) {
    fun getAllBank(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getBank(map)
                .enqueue(object : Callback<ResponseGetBank> {

                    override fun onFailure(call: Call<ResponseGetBank>, t: Throwable) {
                        bankInterface.onErrorGetBank(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseGetBank>, response: Response<ResponseGetBank>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            bankInterface.onSuccessGetBank(data)
                        }else{
                            val error = response.errorBody().toString()
                            bankInterface.onErrorGetBank(error)
                        }
                    }

                })
    }
}
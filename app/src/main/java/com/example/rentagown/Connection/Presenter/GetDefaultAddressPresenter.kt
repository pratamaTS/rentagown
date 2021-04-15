 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Interface.GetDefaultAddressInterface
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.Promo.ResponsePromo
import com.example.rentagown.v2.data.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDefaultAddressPresenter(val getDefaultAddressInterface: GetDefaultAddressInterface) {
    fun getDefaultAddress(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getDefaultAddress(map)
                .enqueue(object : Callback<Address> {

                    override fun onFailure(call: Call<Address>, t: Throwable) {
                        getDefaultAddressInterface.onErrorGetDefaultAddress(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<Address>, response: Response<Address>) {
                        if(response.isSuccessful){
                            val data = response.body()
                            data?.let { getDefaultAddressInterface.onSuccessGetDefaultAddress(it) }
                        }else{
                            val error = response.errorBody().toString()
                            getDefaultAddressInterface.onErrorGetDefaultAddress(error)
                        }
                    }

                })
    }
}
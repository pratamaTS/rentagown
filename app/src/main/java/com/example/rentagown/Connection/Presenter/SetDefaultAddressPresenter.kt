 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Interface.SetDefaultAddressInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.ReqSetAddress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetDefaultAddressPresenter(val setDefaultAddressInterface: SetDefaultAddressInterface) {
    private lateinit var sessionManager: SessionManager

    fun setDefaultAddress(context: Context, reqSetAddress: ReqSetAddress){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .setDefaultAddress(reqSetAddress)
                .enqueue(object : Callback<Address> {

                    override fun onFailure(call: Call<Address>, t: Throwable) {
                        setDefaultAddressInterface.onErrorSetDefaultAddress(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<Address>, response: Response<Address>) {
                        if(response.isSuccessful){
                            val data = response.body()
                            setDefaultAddressInterface.onSuccessSetDefaultAddress(data!!)
                        }else{
                            val error = response.errorBody().toString()
                            setDefaultAddressInterface.onErrorSetDefaultAddress(error)
                        }
                    }

                })
    }
}
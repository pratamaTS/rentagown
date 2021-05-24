 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.NewGownInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.NewGown.ResponseNewGown
import com.example.rentagown.Response.ProductCategory.ResponseProductCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewGownPresenter(val newGownInterface: NewGownInterface) {
    fun getAllNewGown(){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfig.service()
                .getAllNewGown(map)
                .enqueue(object : Callback<ResponseNewGown> {

                    override fun onFailure(call: Call<ResponseNewGown>, t: Throwable) {
                        newGownInterface.onErrorGetNewGown(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseNewGown>, response: Response<ResponseNewGown>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            newGownInterface.onSuccessGetNewGown(data)
                        }else{
                            val error = response.errorBody().toString()
                            newGownInterface.onErrorGetNewGown(error)
                        }
                    }

                })
    }
}
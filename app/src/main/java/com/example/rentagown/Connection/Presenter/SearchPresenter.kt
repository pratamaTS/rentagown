 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Interface.SearchInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.Search.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(val searchInterface: SearchInterface) {

    fun search(productName: String){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["product_name"] = productName

        //Connect
        NetworkConfig.service()
                .search(queryMap, map)
                .enqueue(object : Callback<ResponseSearch> {

                    override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                        searchInterface.onErrorGetProductBySearch(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseSearch>, response: Response<ResponseSearch>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            searchInterface.onSuccessGetProductBySearch(data)
                        }else{
                            val error = response.errorBody().toString()
                            searchInterface.onErrorGetProductBySearch(error)
                        }
                    }

                })
    }
}
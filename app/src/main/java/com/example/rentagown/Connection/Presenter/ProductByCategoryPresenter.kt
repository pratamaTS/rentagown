package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductByCategoryPresenter(val productByCategorvyInterface: ProductByCategoryInterface) {
    fun getAllProductByCategory(category: String){
        //Declare Variable
        val url: String = "api/v/2/product/findcat/" + category

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfig.service()
                .getAllProductByCategory(url, map)
                .enqueue(object : Callback<ResponseProduct> {

                    override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                        productByCategorvyInterface.onErrorGetProductByCategory(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            productByCategorvyInterface.onSuccessGetProductByCategory(data)
                        }else{
                            val error = response.errorBody().toString()
                            productByCategorvyInterface.onErrorGetProductByCategory(error)
                        }
                    }

                })
    }
}
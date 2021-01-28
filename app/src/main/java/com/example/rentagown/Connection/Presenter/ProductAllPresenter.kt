package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.ProductAllInterface
import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductAllPresenter(val productAllInterface: ProductAllInterface) {
    fun getAllProduct(){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfig.service()
                .getAllProduct(map)
                .enqueue(object : Callback<ResponseProduct> {

                    override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                        productAllInterface.onErrorGetAllProduct(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            productAllInterface.onSuccessGetAllProduct(data)
                        }else{
                            val error = response.errorBody().toString()
                            productAllInterface.onErrorGetAllProduct(error)
                        }
                    }

                })
    }
}
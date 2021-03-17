package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Interface.ProductByPromoInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductByPromoPresenter(val productByPromoInterface: ProductByPromoInterface) {
    fun getAllProductByPromo(){
        //Declare Variable
        val url: String = "api/v/2/product/find/promo"

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfig.service()
                .getAllProductByPromo(url, map)
                .enqueue(object : Callback<ResponseProduct> {

                    override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                        productByPromoInterface.onErrorGetProductByPromo(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            productByPromoInterface.onSuccessGetProductByPromo(data)
                        }else{
                            val error = response.errorBody().toString()
                            productByPromoInterface.onErrorGetProductByPromo(error)
                        }
                    }

                })
    }
}
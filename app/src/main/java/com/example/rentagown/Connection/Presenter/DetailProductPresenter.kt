 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.DetailProductInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseDetailProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductPresenter(val detailProductInterface: DetailProductInterface) {
    fun getDetailProduct(idProduct: String){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //URL
        val url: String = "api/v/2/product/findid/" + idProduct

        //Connect
        NetworkConfig.service()
                .getDetailProduct(url, map)
                .enqueue(object : Callback<ResponseDetailProduct> {

                    override fun onFailure(call: Call<ResponseDetailProduct>, t: Throwable) {
                        detailProductInterface.onErrorGetDetailProduct(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseDetailProduct>, response: Response<ResponseDetailProduct>) {
                        if(response.isSuccessful){
                            val dataDetail = response.body()?.data
                            detailProductInterface.onSuccessGetDetailProduct(dataDetail)
                        }else{
                            val error = response.errorBody().toString()
                            detailProductInterface.onErrorGetDetailProduct(error)
                        }
                    }

                })
    }
}
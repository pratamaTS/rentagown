package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Interface.ProductCategoryInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseProduct
import com.example.rentagown.Response.ProductCategory.ResponseProductCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductCategoryPresenter(val productCategoryInterface: ProductCategoryInterface) {
    fun getAllProductCategory(){
        //Declare Variable

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfig.service()
                .getAllProductCategory(map)
                .enqueue(object : Callback<ResponseProductCategory> {

                    override fun onFailure(call: Call<ResponseProductCategory>, t: Throwable) {
                        productCategoryInterface.onErrorGetProductCategory(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseProductCategory>, response: Response<ResponseProductCategory>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            productCategoryInterface.onSuccessGetProductCategory(data)
                        }else{
                            val error = response.errorBody().toString()
                            productCategoryInterface.onErrorGetProductCategory(error)
                        }
                    }

                })
    }
}
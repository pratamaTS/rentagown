 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.DetailProductInterface
import com.example.rentagown.Connection.Interface.PromoByIdInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.Product.ResponseDetailProduct
import com.example.rentagown.Response.Promo.PromoDetail.ResponsePromoDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoByIdPresenter(val promoByIdInterface: PromoByIdInterface) {
    fun getPromoById(idPromo: String){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //URL
        val url: String = "api/v/2/promo/findid/" + idPromo

        //Connect
        NetworkConfig.service()
                .getPromoById(url, map)
                .enqueue(object : Callback<ResponsePromoDetail> {

                    override fun onFailure(call: Call<ResponsePromoDetail>, t: Throwable) {
                        promoByIdInterface.onErrorGetPromoById(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponsePromoDetail>, response: Response<ResponsePromoDetail>) {
                        if(response.isSuccessful){
                            val dataDetail = response.body()?.data
                            promoByIdInterface.onSuccessGetPromoById(dataDetail)
                        }else{
                            val error = response.errorBody().toString()
                            promoByIdInterface.onErrorGetPromoById(error)
                        }
                    }

                })
    }
}
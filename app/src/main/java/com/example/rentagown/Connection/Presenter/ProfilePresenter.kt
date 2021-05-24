 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Connection.Interface.DetailProductInterface
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Interface.PromoByIdInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.Product.ResponseDetailProduct
import com.example.rentagown.Response.Profile.ResponseProfile
import com.example.rentagown.Response.Promo.PromoDetail.ResponsePromoDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(val profileInterface: ProfileInterface) {

    fun getProfile(context: Context){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "apps.rentagown.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .getProfile(map)
                .enqueue(object : Callback<ResponseProfile> {

                    override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                        profileInterface.onErrorGetProfile(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseProfile>, response: Response<ResponseProfile>) {
                        if(response.isSuccessful){
                            val dataDetail = response.body()?.data
                            profileInterface.onSuccessGetProfile(dataDetail)
                        }else{
                            val error = response.errorBody().toString()
                            profileInterface.onErrorGetProfile(error)
                        }
                    }

                })
    }

}
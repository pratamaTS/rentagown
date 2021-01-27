 package com.example.rentagown.Connection.Presenter

import com.example.rentagown.Connection.Interface.FavoriteGownInterface
import com.example.rentagown.Connection.Interface.NewGownInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Response.FavoriteGown.ResponseFavoriteGown
import com.example.rentagown.Response.NewGown.ResponseNewGown
import com.example.rentagown.Response.ProductCategory.ResponseProductCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteGownPresenter(val favoriteGownInterface: FavoriteGownInterface) {
    fun getAllFavoriteGown(){
        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfig.service()
                .getAllFavoriteGown(map)
                .enqueue(object : Callback<ResponseFavoriteGown> {

                    override fun onFailure(call: Call<ResponseFavoriteGown>, t: Throwable) {
                        favoriteGownInterface.onErrorGetFavoriteGown(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseFavoriteGown>, response: Response<ResponseFavoriteGown>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            favoriteGownInterface.onSuccessGetFavoriteGown(data)
                        }else{
                            val error = response.errorBody().toString()
                            favoriteGownInterface.onErrorGetFavoriteGown(error)
                        }
                    }

                })
    }
}
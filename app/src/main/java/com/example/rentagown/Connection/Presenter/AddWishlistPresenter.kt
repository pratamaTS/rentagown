 package com.example.rentagown.Connection.Presenter

import android.content.Context
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Body.WishlistBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Interface.AddWishlistInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.CreateWishlist.ResponseCreateWishlist
import com.example.rentagown.Response.Login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddWishlistPresenter(val wishlistInterface: AddWishlistInterface) {
    private lateinit var sessionManager: SessionManager

    fun addWishlist(context: Context, wishlistBody: WishlistBody){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect
        NetworkConfigAfterLogin.service(context)
                .addWishlist(map, wishlistBody)
                .enqueue(object : Callback<ResponseCreateWishlist> {

                    override fun onFailure(call: Call<ResponseCreateWishlist>, t: Throwable) {
                        wishlistInterface.onErrorAddWishlist(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ResponseCreateWishlist>, response: Response<ResponseCreateWishlist>) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            wishlistInterface.onSuccessAddWishlist(data!!)
                        }else{
                            val error = response.errorBody().toString()
                            wishlistInterface.onErrorAddWishlist(error)
                        }
                    }

                })
    }
}
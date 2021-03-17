 package com.example.rentagown.Connection.Presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.rentagown.Connection.Interface.EditProfileInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.Run
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.Model.UpdateProfile
import com.example.rentagown.Response.EditProfile.ResponseEditProfile
import com.example.rentagown.v2.util.ErrorUtils.parseError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


 class EditProfilePresenter(val editProfileInterface: EditProfileInterface) {

    fun editProfile(context: Context, updateProfile: UpdateProfile){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect

        Run.after(1000, {
            NetworkConfigAfterLogin.service(context)
                    .updateProfile(map, updateProfile)
                    .enqueue(object : Callback<ResponseEditProfile> {

                        override fun onFailure(call: Call<ResponseEditProfile>, t: Throwable) {
                            editProfileInterface.onErrorEditProfile(t.localizedMessage)
                        }

                        override fun onResponse(call: Call<ResponseEditProfile>, response: Response<ResponseEditProfile>) {
                            if (response.isSuccessful) {
                                val data = response.body()?.data
                                editProfileInterface.onSuccessEditProfile(data!!)
                            } else {
                                val error: ResponseError? = parseError(response, context)
                                // … and use it to show error information

                                // … or just log the issue like we’re doing :)
                                // … and use it to show error information

                                // … or just log the issue like we’re doing :)
                                Log.d("error message", error?.error.toString())
                                editProfileInterface.onErrorEditProfile(error?.error.toString())
                            }
                        }

                    })
        })
    }
}
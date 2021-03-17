 package com.example.rentagown.Connection.Presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.rentagown.Connection.Interface.ChangePasswordInterface
import com.example.rentagown.Connection.Interface.EditProfileInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.Run
import com.example.rentagown.Model.ChangePassword
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.Model.UpdateProfile
import com.example.rentagown.Response.ChangePassword.ResponseChangePassword
import com.example.rentagown.Response.EditProfile.ResponseEditProfile
import com.example.rentagown.v2.util.ErrorUtils.parseError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


 class ChangePasswordPresenter(val changePasswordInterface: ChangePasswordInterface) {

    fun changePassword(context: Context, changePassword: ChangePassword){

        //Header
        val map: MutableMap<String, String> = HashMap()
        map["Accept-Encoding"] = "gzip, deflate, br"
        map["Content-Type"] = "application/json"
        map["Host"] = "absdigital.id"

        //Connect

        Run.after(1000, {
            NetworkConfigAfterLogin.service(context)
                    .changePassword(map, changePassword)
                    .enqueue(object : Callback<ResponseChangePassword> {

                        override fun onFailure(call: Call<ResponseChangePassword>, t: Throwable) {
                            changePasswordInterface.onErrorChangePassword(t.localizedMessage)
                        }

                        override fun onResponse(call: Call<ResponseChangePassword>, response: Response<ResponseChangePassword>) {
                            if (response.isSuccessful) {
                                val data = response.body()?.data
                                changePasswordInterface.onSuccessChangePassword(data!!)
                            } else {
                                val error: ResponseError? = parseError(response, context)
                                // … and use it to show error information

                                // … or just log the issue like we’re doing :)
                                // … and use it to show error information

                                // … or just log the issue like we’re doing :)
                                Log.d("error message", error?.error.toString())
                                changePasswordInterface.onErrorChangePassword(error?.error.toString())
                            }
                        }

                    })
        })
    }
}
 package com.example.rentagown.Connection.Presenter

import android.content.Context
import android.widget.Toast
import com.example.rentagown.Connection.Interface.UploadPictInterface
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.Run
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.Response.EditProfile.ResponseUploadPict
import com.example.rentagown.v2.util.ErrorUtils
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class UploadPictPresenter(val uploadPictInterface: UploadPictInterface) {
    fun uploadProfilePict(context: Context, body: MultipartBody.Part){

        //Connect
        Run.after(1000, {
            NetworkConfigAfterLogin.service(context)
                    .uploadProfilePict(body)
                    .enqueue(object : Callback<ResponseUploadPict> {

                        override fun onFailure(call: Call<ResponseUploadPict>, t: Throwable) {
                            uploadPictInterface.onErrorGetUploadPict(t.localizedMessage)
                        }

                        override fun onResponse(call: Call<ResponseUploadPict>, response: Response<ResponseUploadPict>) {
                            if(response.isSuccessful){
                                val dataPict = response.body()?.data
                                if (dataPict != null) {
                                    uploadPictInterface.onSuccessUploadPict(dataPict)
                                }else{
                                    Toast.makeText(context, "Error after upload, please contact admin", Toast.LENGTH_SHORT)
                                }
                            }else{
                                val error: ResponseError? = ErrorUtils.parseError(response, context)
                                uploadPictInterface.onErrorGetUploadPict(error?.error.toString())
                            }
                        }

                    })
        })
    }
}
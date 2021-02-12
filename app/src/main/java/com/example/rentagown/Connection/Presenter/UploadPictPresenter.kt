 package com.example.rentagown.Connection.Presenter

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import com.example.rentagown.Connection.Interface.DetailProductInterface
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Interface.PromoByIdInterface
import com.example.rentagown.Connection.Interface.UploadPictInterface
import com.example.rentagown.Connection.NetworkConfig
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Response.EditProfile.ResponseUploadPict
import com.example.rentagown.Response.Product.ResponseDetailProduct
import com.example.rentagown.Response.Profile.ResponseProfile
import com.example.rentagown.Response.Promo.PromoDetail.ResponsePromoDetail
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

 class UploadPictPresenter(val uploadPictInterface: UploadPictInterface) {
    fun uploadProfilePict(context: Context, body: Bitmap){
        val bodyMap = HashMap<String, Bitmap>()

        // Body
        bodyMap.put("avatar", body)

        //Connect
        NetworkConfigAfterLogin.service(context)
                .uploadProfilePict(bodyMap)
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
                            val error = response.errorBody().toString()
                            uploadPictInterface.onErrorGetUploadPict(error)
                        }
                    }

                })
    }
}
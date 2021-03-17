package com.example.rentagown.v2.util

import android.content.Context
import com.example.rentagown.Connection.NetworkConfigAfterLogin
import com.example.rentagown.Connection.RentaGownServices
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.v2.data.network.RAGApi
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException


object ErrorUtils {
    fun parseError(response: retrofit2.Response<*>, context: Context): ResponseError? {
        val converter: Converter<ResponseBody, ResponseError> = NetworkConfigAfterLogin.getRetrofit(context)
                .responseBodyConverter(ResponseError::class.java, arrayOfNulls<Annotation>(0))
        val error: ResponseError?
        error = try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return ResponseError()
        }
        return error
    }
}
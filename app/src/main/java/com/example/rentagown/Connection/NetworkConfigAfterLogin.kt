package com.example.rentagown.Connection

import android.content.Context
import com.example.rentagown.Connection.Interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfigAfterLogin {
    //untuk melakukan logging untuk melihat logcat
    fun getInterceptor(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(interceptor)
            .build()
    }

    //mengirim dan menerima response dari server
    fun getRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.SERVER_URL)
            .client(getInterceptor(context))
            .addConverterFactory(GsonConverterFactory.create()) //convert data Json yang diterima dari server menjadi objek
            .build()
    }

    fun service(context: Context) = getRetrofit(context).create(RentaGownServices::class.java)
}
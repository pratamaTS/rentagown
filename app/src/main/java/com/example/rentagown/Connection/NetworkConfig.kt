package com.example.rentagown.Connection

import com.example.rentagown.BuildConfig
import com.example.rentagown.Connection.Interface.RentaGownServices
import com.example.rentagown.Connection.NetworkConfig.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {
    val SERVER_URL: String ="http://absdigital.id:5000"
    
    //untuk melakukan logging untuk melihat logcat
    fun getInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    //mengirim dan menerima response dari server
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create()) //convert data Json yang diterima dari server menjadi objek
            .build()
    }

    fun service() = getRetrofit().create(RentaGownServices::class.java)
}
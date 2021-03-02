package com.example.rentagown.v2.data.network

import com.example.rentagown.v2.data.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RAGApiService {

    /* ADDRESS */

    @GET("userdetail/find")
    fun getUserAddresses(): Single<BasePagingResp<Address>>

    @POST("userdetail")
    fun addUserAddress(@Body address: Address): Single<BaseResp<Address>>

    @PUT("userdetail/update/{addressId}")
    fun updateUserAddress(@Path("addressId") addressId: String, @Body address: Address): Single<BaseResp<Address>>

    @HTTP(method = "DELETE", path = "userdetail/{addressId}", hasBody = true)
    fun deleteUserAddress(@Path("addressId") addressId: String, @Body reqDeleteAddress: ReqDeleteAddress): Single<BaseResp<Address>>

    /* BANK */
    @GET("bank/findall")
    fun getBanks(): Single<BasePagingResp<Bank>>

    /* PRODUCTS */

    @GET("product/findid/{productId}")
    fun getProductDetail(@Path("productId") productId: String): Single<BaseResp<Product>>

    @GET("product/findcat/{categoryName}")
    fun getSimilarProducts(@Path("categoryName") categoryName: String): Single<BasePagingResp<Product>>

    /*  BOOKING  */
    @GET("fitting/{fittingId}")
    fun getFitting(@Path("fittingId") fittingId: String): Single<BaseResp<Fitting>>

    @POST("fitting")
    fun saveFitting(@Body fitting: Fitting): Single<BaseResp<Fitting>>

    @GET("booking/date/{productId}")
    fun getProductUnavailableDates(@Path("productId") productId: String): Single<BasePagingResp<UnavailableDate>>

    @POST("booking")
    fun createBooking(@Body createBooking: ReqCreateBooking): Single<BaseResp<Booking>>

    @GET("booking/mybooking")
    fun getMyBookings(): Single<BasePagingResp<Booking>>

    @GET("booking/myhistory")
    fun getMyBookingsHistory(): Single<BasePagingResp<Booking>>

    @PUT("booking/updatepayment/{transactionId}")
    fun confirmPayment(@Path("transactionId") transactionId: String, @Body reqConfirmPayment: ReqConfirmPayment): Single<BaseResp<Booking>>

    @PUT("booking/updaterepayment/{transactionId}")
    fun confirm2ndPayment(@Path("transactionId") transactionId: String, @Body reqConfirmPayment: ReqConfirm2ndPayment): Single<BaseResp<Booking>>

    @POST("rating")
    fun reviewBooking(@Body createReview: ReqReviewBooking): Single<BaseResp<ProductReview>>

    @GET("booking/invoice")
    fun getMyInvoices(): Single<BasePagingResp<Invoice>>

}
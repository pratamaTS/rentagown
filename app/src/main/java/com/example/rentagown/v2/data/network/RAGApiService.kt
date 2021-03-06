package com.example.rentagown.v2.data.network

import com.example.rentagown.v2.data.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RAGApiService {

    /* ADDRESS */

    @GET("userdetail/find")
    fun getUserAddresses(): Single<BasePagingResp<Address>>

    @GET("userdetail/find/default")
    fun getDefaultAddress(): Single<BaseResp<Address>>

    @GET("bank/find/default")
    fun getDefaultBank(): Single<BaseResp<Bank>>

    @POST("userdetail")
    fun addUserAddress(@Body address: Address): Single<BaseResp<Address>>

    @PUT("userdetail/update/{addressId}")
    fun updateUserAddress(@Path("addressId") addressId: String, @Body address: Address): Single<BaseResp<Address>>

    @PUT("userdetail/default")
    fun setDefaultAddress(@Body reqSetAddress: ReqSetAddress): Single<BaseResp<Address>>

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

    @POST("booking/checkdate/{productId}")
    fun checkDate(@Path("productId") productID: String, @Body reqCheckDate: ReqCheckDate): Single<DateResponse>

    @POST("fitting")
    fun saveFitting(@Body fitting: Fitting): Single<BaseResp<Fitting>>

    @GET("booking/date/{productId}")
    fun getProductUnavailableDates(@Path("productId") productId: String): Single<BasePagingResp<UnavailableDate>>

    @POST("booking")
    fun createBooking(@Body createBooking: ReqCreateBooking): Single<BaseResp<Booking>>

    @PUT("booking/usercancel")
    fun cancelBooking(@Body cancelBooking: ReqCancelBooking): Single<BaseResp<Booking>>

    @GET("booking/mybooking")
    fun getMyBookings(): Single<BasePagingResp<Booking>>

    @GET("mstbank/findall")
    fun getBank(): Single<BasePagingResp<MasterBank>>

    @GET("booking/myhistory")
    fun getMyBookingsHistory(): Single<BasePagingResp<Booking>>

    @PUT("booking/update/confirm/{transactionId}")
    fun confirmPayment(@Path("transactionId") transactionId: String, @Body reqConfirmPayment: ReqConfirmPayment): Single<BaseResp<Booking>>

    @PUT("booking/updaterepayment/{transactionId}")
    fun confirm2ndPayment(@Path("transactionId") transactionId: String, @Body reqConfirmPayment: ReqConfirm2ndPayment): Single<BaseResp<Booking>>

    @POST("rating")
    fun reviewBooking(@Body createReview: ReqReviewBooking): Single<BaseResp<ProductReview>>

    @GET("invoice/myinvoice")
    fun getMyInvoices(): Single<BasePagingResp<InvoiceHistory>>

    @GET("invoice/findid/{invoiceId}")
    fun getInvoiceDetail(@Path("invoiceId") productId: String): Single<BaseResp<Invoice>>

    @GET("rating/{productId}")
    fun getProductRatings(@Path("productId") productId: String): Single<BasePagingResp<ProductReview>>

}
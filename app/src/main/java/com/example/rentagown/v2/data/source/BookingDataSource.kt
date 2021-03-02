package com.example.rentagown.v2.data.source

import com.example.rentagown.v2.data.model.*
import io.reactivex.rxjava3.core.Single

interface BookingDataSource {

    fun getFitting(fittingId: String) : Single<BaseResp<Fitting>>
    fun saveFitting(fitting: Fitting) : Single<BaseResp<Fitting>>
    fun getProductUnavailableDates(productId: String) : Single<BasePagingResp<UnavailableDate>>
    fun createBooking(createBooking: ReqCreateBooking) : Single<BaseResp<Booking>>
    fun getMyBookings() : Single<BasePagingResp<Booking>>
    fun getMyBookingsHistory() : Single<BasePagingResp<Booking>>
    fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment) : Single<BaseResp<Booking>>
    fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment) : Single<BaseResp<Booking>>
    fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking) : Single<BaseResp<ProductReview>>
    fun getMyInvoices() : Single<BasePagingResp<Invoice>>

}
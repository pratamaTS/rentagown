package com.example.rentagown.v2.data.source

import com.example.rentagown.v2.data.model.*
import io.reactivex.rxjava3.core.Single

interface BookingDataSource {

    fun getBank() : Single<BasePagingResp<MasterBank>>
    fun getFitting(fittingId: String) : Single<BaseResp<Fitting>>
    fun saveFitting(fitting: Fitting) : Single<BaseResp<Fitting>>
    fun getProductUnavailableDates(productId: String) : Single<BasePagingResp<UnavailableDate>>
    fun createBooking(createBooking: ReqCreateBooking) : Single<BaseResp<Booking>>
    fun checkDate(productID: String, reqCheckDate: ReqCheckDate) : Single<DateResponse>
    fun cancelBooking(reqCancel: ReqCancelBooking) : Single<BaseResp<Booking>>
    fun getMyBookings() : Single<BasePagingResp<Booking>>
    fun getMyBookingsHistory() : Single<BasePagingResp<Booking>>
    fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment) : Single<BaseResp<Booking>>
    fun setDefaultAddress(reqSetAddress: ReqSetAddress) : Single<BaseResp<Address>>
    fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment) : Single<BaseResp<Booking>>
    fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking) : Single<BaseResp<ProductReview>>
    fun getMyInvoices() : Single<BasePagingResp<InvoiceHistory>>
    fun getInvoiceDetail(invoiceId: String) : Single<BaseResp<Invoice>>
    fun getDefaultAddress() : Single<BaseResp<Address>>
    fun getDefaultBank() : Single<BaseResp<Bank>>

}
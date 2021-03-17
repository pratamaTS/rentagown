package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.model.*
import com.example.rentagown.v2.data.network.RAGApiService
import com.example.rentagown.v2.data.source.BookingDataSource
import io.reactivex.rxjava3.core.Single

class BookingRemoteRepository(private val apiService: RAGApiService) : BookingDataSource {

    override fun getFitting(fittingId: String): Single<BaseResp<Fitting>> {
        return apiService.getFitting(fittingId)
    }

    override fun saveFitting(fitting: Fitting): Single<BaseResp<Fitting>> {
        return apiService.saveFitting(fitting)
    }

    override fun getProductUnavailableDates(productId: String): Single<BasePagingResp<UnavailableDate>> {
        return apiService.getProductUnavailableDates(productId)
    }

    override fun createBooking(createBooking: ReqCreateBooking): Single<BaseResp<Booking>> {
        return apiService.createBooking(createBooking)
    }

    override fun cancelBooking(reqCancel: ReqCancelBooking): Single<BaseResp<Booking>> {
        return apiService.cancelBooking(reqCancel)
    }

    override fun getMyBookings(): Single<BasePagingResp<Booking>> {
        return apiService.getMyBookings()
    }

    override fun getBank(): Single<BasePagingResp<MasterBank>> {
        return apiService.getBank()
    }

    override fun getMyBookingsHistory(): Single<BasePagingResp<Booking>> {
        return apiService.getMyBookingsHistory()
    }

    override fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment): Single<BaseResp<Booking>> {
        return apiService.confirmPayment(transactionId, reqConfirmPayment)
    }

    override fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment): Single<BaseResp<Booking>> {
        return apiService.confirm2ndPayment(transactionId, reqConfirmPayment)
    }

    override fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking): Single<BaseResp<ProductReview>> {
        return apiService.reviewBooking(reqCreateReviewBooking)
    }

    override fun getMyInvoices(): Single<BasePagingResp<InvoiceHistory>> {
        return apiService.getMyInvoices()
    }

    override fun getInvoiceDetail(invoiceId: String): Single<BaseResp<Invoice>> {
        return apiService.getInvoiceDetail(invoiceId)
    }

}
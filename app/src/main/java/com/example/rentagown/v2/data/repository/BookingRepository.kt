package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.model.*
import com.example.rentagown.v2.data.source.BookingDataSource
import io.reactivex.rxjava3.core.Single

class BookingRepository(private val remoteRepository: BookingDataSource) : BookingDataSource {

    override fun getFitting(fittingId: String): Single<BaseResp<Fitting>> {
        return remoteRepository.getFitting(fittingId)
    }

    override fun saveFitting(fitting: Fitting): Single<BaseResp<Fitting>> {
        return remoteRepository.saveFitting(fitting)
    }

    override fun getProductUnavailableDates(productId: String): Single<BasePagingResp<UnavailableDate>> {
        return remoteRepository.getProductUnavailableDates(productId)
    }

    override fun createBooking(createBooking: ReqCreateBooking): Single<BaseResp<Booking>> {
        return remoteRepository.createBooking(createBooking)
    }

    override fun cancelBooking(transactionId: String): Single<BaseResp<Booking>> {
        return remoteRepository.cancelBooking(transactionId)
    }

    override fun getMyBookings(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookings()
    }

    override fun getMyBookingsHistory(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookingsHistory()
    }

    override fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment): Single<BaseResp<Booking>> {
        return remoteRepository.confirmPayment(transactionId, reqConfirmPayment)
    }

    override fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment): Single<BaseResp<Booking>> {
        return remoteRepository.confirm2ndPayment(transactionId, reqConfirmPayment)
    }

    override fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking): Single<BaseResp<ProductReview>> {
        return remoteRepository.reviewBooking(reqCreateReviewBooking)
    }

    override fun getMyInvoices(): Single<BasePagingResp<Invoice>> {
        return remoteRepository.getMyInvoices()
    }

}
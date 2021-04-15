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

    override fun checkDate(productID: String, reqCheckDate: ReqCheckDate): Single<DateResponse> {
        return remoteRepository.checkDate(productID, reqCheckDate)
    }

    override fun cancelBooking(reqCancel: ReqCancelBooking): Single<BaseResp<Booking>> {
        return remoteRepository.cancelBooking(reqCancel)
    }

    override fun getMyBookings(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookings()
    }

    override fun getBank(): Single<BasePagingResp<MasterBank>> {
        return remoteRepository.getBank()
    }

    override fun getMyBookingsHistory(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookingsHistory()
    }

    override fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment): Single<BaseResp<Booking>> {
        return remoteRepository.confirmPayment(transactionId, reqConfirmPayment)
    }

    override fun setDefaultAddress(reqSetAddress: ReqSetAddress): Single<BaseResp<Address>> {
        return remoteRepository.setDefaultAddress(reqSetAddress)
    }

    override fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment): Single<BaseResp<Booking>> {
        return remoteRepository.confirm2ndPayment(transactionId, reqConfirmPayment)
    }

    override fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking): Single<BaseResp<ProductReview>> {
        return remoteRepository.reviewBooking(reqCreateReviewBooking)
    }

    override fun getMyInvoices(): Single<BasePagingResp<InvoiceHistory>> {
        return remoteRepository.getMyInvoices()
    }

    override fun getInvoiceDetail(invoiceId: String): Single<BaseResp<Invoice>> {
        return remoteRepository.getInvoiceDetail(invoiceId)
    }

    override fun getDefaultAddress(): Single<Address> {
        return remoteRepository.getDefaultAddress()
    }

}
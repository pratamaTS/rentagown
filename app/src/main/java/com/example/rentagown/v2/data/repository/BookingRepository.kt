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

    override fun getMyBookings(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookings()
    }

    override fun getMyBookingsHistory(): Single<BasePagingResp<Booking>> {
        return remoteRepository.getMyBookingsHistory()
    }

}
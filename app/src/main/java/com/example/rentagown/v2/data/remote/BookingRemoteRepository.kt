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

    override fun getMyBookings(): Single<BasePagingResp<Booking>> {
        return apiService.getMyBookings()
    }

    override fun getMyBookingsHistory(): Single<BasePagingResp<Booking>> {
        return apiService.getMyBookingsHistory()
    }

}
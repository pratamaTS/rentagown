package com.example.rentagown.v2.ui.unvailabledates

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.UnavailableDate
import com.example.rentagown.v2.data.source.BookingDataSource

class UnavailableDatesPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<UnavailableDatesContract.View>(), UnavailableDatesContract.Presenter {

    override fun loadUnavailableDates(productId: String) {
        view?.showLoadingContent(true)
        safeCallPaging(repository.getProductUnavailableDates(productId), object : Listener<List<UnavailableDate>> {
            override fun onSuccess(data: List<UnavailableDate>?) {
                view?.showUnavailableDates(data ?: listOf())
            }
        })

    }

}
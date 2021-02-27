package com.example.rentagown.v2.ui.unvailabledates

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.UnavailableDate

class UnavailableDatesContract {

    interface View : BaseView<Presenter> {

        fun showUnavailableDates(dates: List<UnavailableDate>)

    }

    interface Presenter : BasePresenter {

        fun loadUnavailableDates(productId: String)

    }


}
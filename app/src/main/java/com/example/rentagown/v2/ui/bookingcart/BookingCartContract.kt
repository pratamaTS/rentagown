package com.example.rentagown.v2.ui.bookingcart

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ReqCreateBooking

class BookingCartContract {

    interface View : BaseView<Presenter> {

        fun setDataToView(createBooking: ReqCreateBooking, product: Product)
        fun getReqCreateBookingData(): ReqCreateBooking?
        fun getProductData(): Product?

        fun navigateToWhatsapp(phoneNumber: String)
        fun navigateToBooking(createBooking: ReqCreateBooking, product: Product)

        fun showMsgBookingNotFound()

        fun setResultBookingCreated()

    }

    interface Presenter : BasePresenter {


        fun onBtnDeleteClicked()
        fun onBtnWhatsappClicked()
        fun onBtnPayClicked()

        fun onBookingCreated()


    }

}
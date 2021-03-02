package com.example.rentagown.v2.ui.bookingcart

import com.example.rentagown.v2.base.BaseRAGPresenter

class BookingCartPresenter : BaseRAGPresenter<BookingCartContract.View>(), BookingCartContract.Presenter {

    override fun start() {
        super.start()

        val createBooking = view?.getReqCreateBookingData()
        val product = view?.getProductData()

        if(createBooking == null || product == null) {
            view?.showMsgBookingNotFound()
            return
        }

        view?.setDataToView(createBooking, product)
    }

    override fun onBtnDeleteClicked() {
        view?.closeView()
    }

    override fun onBtnWhatsappClicked() {
        val number = "+6281806155676"
        view?.navigateToWhatsapp(number)
    }

    override fun onBtnPayClicked() {
        val createBooking = view?.getReqCreateBookingData()
        val product = view?.getProductData()

        if(createBooking == null || product == null) {
            view?.showMsgBookingNotFound()
            return
        }

        view?.navigateToBooking(createBooking, product)
    }

    override fun onBookingCreated() {
        view?.setResultBookingCreated()
        view?.closeView()
    }

}
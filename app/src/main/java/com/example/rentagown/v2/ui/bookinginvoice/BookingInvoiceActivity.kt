package com.example.rentagown.v2.ui.bookinginvoice

import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity

class BookingInvoiceActivity : BaseRAGActivity<BookingInvoiceContract.Presenter>(), BookingInvoiceContract.View {

    override val layoutId: Int = R.layout.activity_booking_invoice_v2

    override lateinit var presenter: BookingInvoiceContract.Presenter

    override fun init() {
        super.init()

        presenter = BookingInvoicePresenter()
    }


}
package com.example.rentagown.v2.ui.invoicedetail

import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity

class InvoiceDetailActivity : BaseRAGActivity<InvoiceDetailContract.Presenter>(), InvoiceDetailContract.View {

    companion object {

        const val REQ_VIEW_INVOICE_DETAIL = 253
        const val RES_INVOICE_PAID = 252

    }


    override val layoutId: Int = R.layout.activity_invoice_detail_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: InvoiceDetailContract.Presenter

    override fun init() {
        super.init()

        presenter = InvoiceDetailPresenter()
    }

    override fun setupWidgets() {
        super.setupWidgets()

    }

}
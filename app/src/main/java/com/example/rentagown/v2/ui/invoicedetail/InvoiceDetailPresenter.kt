package com.example.rentagown.v2.ui.invoicedetail

import com.example.rentagown.v2.base.BaseRAGPresenter

class InvoiceDetailPresenter : BaseRAGPresenter<InvoiceDetailContract.View>(), InvoiceDetailContract.Presenter {

    override fun start() {
        super.start()

        val invoice = view?.getDataInvoice()

        if(invoice == null) {
            view?.showMsgInvoiceNotFound()
            return
        }

        view?.setDataInvoiceToView(invoice)

    }
}
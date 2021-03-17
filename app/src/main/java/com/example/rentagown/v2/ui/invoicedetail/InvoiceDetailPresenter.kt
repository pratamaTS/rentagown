package com.example.rentagown.v2.ui.invoicedetail

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Invoice
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.source.BookingDataSource

class InvoiceDetailPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<InvoiceDetailContract.View>(), InvoiceDetailContract.Presenter {

    override fun start() {
        super.start()

        val invoiceId = view?.getInvoiceId()

        if(invoiceId == null) {
            view?.showMsgInvoiceNotFound()
            return
        }

        getInvoiceDetail(invoiceId)

    }

    override fun getInvoiceDetail(id: String) {
        view?.showLoadingContent(true)

        safeCall(repository.getInvoiceDetail(id), object : Listener<Invoice> {
            override fun onSuccess(data: Invoice?) {
                if (data != null) {
                    view?.setDataInvoiceToView(data)
                }
            }
        })
    }
}
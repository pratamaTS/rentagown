package com.example.rentagown.v2.ui.invoicedetail

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Invoice

class InvoiceDetailContract {

    interface View : BaseView<Presenter> {

        fun setDataInvoiceToView(invoice: Invoice)
        fun getDataInvoice(): Invoice?
        fun showMsgInvoiceNotFound()

    }

    interface Presenter: BasePresenter {



    }

}
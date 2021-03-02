package com.example.rentagown.v2.ui.myinvoices

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Invoice

class MyInvoicesContract {

    interface View : BaseView<Presenter> {

        fun showDataInvoices(invoices: List<Invoice>)
        fun showContentRefreshing(refreshing: Boolean) // bisa dibikinkan base activity baru
        fun navigateToInvoiceDetail(invoice: Invoice)

    }

    interface Presenter : BasePresenter {

        fun loadMyInvoices(refreshing: Boolean = false)
        fun onRefreshContent()
        fun onItemClick(invoice: Invoice)

    }

}
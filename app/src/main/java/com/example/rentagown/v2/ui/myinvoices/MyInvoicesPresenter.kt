package com.example.rentagown.v2.ui.myinvoices

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Invoice
import com.example.rentagown.v2.data.source.BookingDataSource

class MyInvoicesPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<MyInvoicesContract.View>(), MyInvoicesContract.Presenter {

    override fun loadMyInvoices(refreshing: Boolean) {
        if(!refreshing) view?.showLoadingContent(true)

        safeCallPaging(repository.getMyInvoices(), object : Listener<List<Invoice>> {
            override fun onSuccess(data: List<Invoice>?) {
                view?.showContentRefreshing(false)

                val mData = data ?: listOf()

                view?.showEmptyPlaceHolder(mData.isEmpty())
                view?.showDataInvoices(mData)
            }

            override fun onFailed(message: String?) {
                view?.showContentRefreshing(false)
                super.onFailed(message)
            }

        }, RequestConfiguration(updateLoadingContentIndicator = !refreshing))
    }

    override fun onRefreshContent() {
        loadMyInvoices(true)
    }

    override fun onItemClick(invoice: Invoice) {
        view?.navigateToInvoiceDetail(invoice)
    }


    override fun start() {
        super.start()

        loadMyInvoices()
    }

}
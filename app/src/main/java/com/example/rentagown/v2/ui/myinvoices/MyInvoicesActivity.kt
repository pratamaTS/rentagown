package com.example.rentagown.v2.ui.myinvoices

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Invoice
import com.example.rentagown.v2.data.model.InvoiceHistory
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.invoicedetail.InvoiceDetailActivity
import com.example.rentagown.v2.ui.myinvoices.item.MyInvoiceItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

class MyInvoicesActivity : BaseRAGActivity<MyInvoicesContract.Presenter>(), MyInvoicesContract.View {

    override val layoutId: Int = R.layout.activity_my_invoice_v2

    override var btnBackId: Int = R.id.btn_back
    override var contentContainerId: Int = R.id.rv_my_invoices
    override var emptyPlaceholderId: Int = R.id.container_empty_data

    override lateinit var presenter: MyInvoicesContract.Presenter

    private lateinit var adapter: FastAdapter<MyInvoiceItem>
    private lateinit var itemAdapter: ModelAdapter<InvoiceHistory, MyInvoiceItem>

    private lateinit var srRefreshContent: SwipeRefreshLayout
    private lateinit var rvMyInvoices: RecyclerView

    override fun init() {
        super.init()

        presenter = MyInvoicesPresenter(RepositoryLocator
                .getInstance(RemoteRepositoryLocator
                        .getInstance(RAGApi
                                .apiService(this)))
                .bookingRepository)
    }

    override fun setupWidgets() {
        super.setupWidgets()

        srRefreshContent = findViewById(R.id.sr_refresh_content)
        rvMyInvoices = findViewById(R.id.rv_my_invoices)

        srRefreshContent.setOnRefreshListener { presenter.onRefreshContent() }

    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> MyInvoiceItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvMyInvoices.layoutManager = LinearLayoutManager(this)
        rvMyInvoices.adapter = adapter
        rvMyInvoices.itemAnimator = null

        adapter.onClickListener = {
            _, _, item, _ ->
            presenter.onItemClick(item.model)
            false
        }

    }

    override fun showDataInvoices(invoices: List<InvoiceHistory>) {
        itemAdapter.set(invoices)
    }

    override fun showContentRefreshing(refreshing: Boolean) {
        srRefreshContent.isRefreshing = refreshing
    }

    override fun navigateToInvoiceDetail(invoice: InvoiceHistory) {
        Intent(this, InvoiceDetailActivity::class.java).apply {
            putExtra("invoiceId", invoice.id)
            startActivityForResult(this, InvoiceDetailActivity.REQ_VIEW_INVOICE_DETAIL)
        }
    }

}
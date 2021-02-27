package com.example.rentagown.v2.ui.unvailabledates

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.UnavailableDate
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.unvailabledates.item.UnavailableDateItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

class UnavailableDatesActivity : BaseRAGActivity<UnavailableDatesContract.Presenter>(), UnavailableDatesContract.View {

    override val layoutId: Int = R.layout.activity_unavailable_dates_v2

    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: UnavailableDatesContract.Presenter

    lateinit var rvUnavailableDates: RecyclerView

    private lateinit var adapter: FastAdapter<UnavailableDateItem>
    private lateinit var itemAdapter: ModelAdapter<UnavailableDate, UnavailableDateItem>


    override fun init() {
        super.init()

        presenter = UnavailableDatesPresenter(
            RepositoryLocator.getInstance(
                RemoteRepositoryLocator.getInstance(
                    RAGApi.apiService(
                        this
                    )
                )
            ).bookingRepository
        )

    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> UnavailableDateItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvUnavailableDates.layoutManager = LinearLayoutManager(this)
        rvUnavailableDates.adapter = adapter
    }

    override fun setupWidgets() {
        super.setupWidgets()

        rvUnavailableDates = findViewById(R.id.rv_unavailable_dates)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSelectedProductId()?.apply {
            presenter.loadUnavailableDates(this)
        }

    }

    override fun showUnavailableDates(dates: List<UnavailableDate>) {
        itemAdapter.set(dates)
    }

    private fun getSelectedProductId() = intent.getStringExtra("product_id")

}
package com.example.rentagown.v2.ui.mybookinghistory

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.mybookinghistory.item.MyBookingHistoryItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

class MyBookingHistoryActivity : BaseRAGActivity<MyBookingHistoryConstract.Presenter>(), MyBookingHistoryConstract.View,
        View.OnClickListener {

    companion object {
        // harusnya itu call activity langsung dengan intent, tapi saya tidak tahu activity mana yang running (MainACtivity atau MainAfterActivity).
        // tolong diperbaiki nanti

        const val REQ_VIEW_BOOKINGS_HISTORY = 153
        const val RES_BROWSE_PRODUCT = 154
    }

    override val layoutId: Int = R.layout.activity_my_bookings_history_v2

    override var btnBackId: Int = R.id.btn_back
    override var contentContainerId: Int = R.id.rv_my_bookings_history
    override var emptyPlaceholderId: Int = R.id.container_empty_data

    override lateinit var presenter: MyBookingHistoryConstract.Presenter

    private lateinit var adapter: FastAdapter<MyBookingHistoryItem>
    private lateinit var itemAdapter: ModelAdapter<Booking, MyBookingHistoryItem>

    private lateinit var rvMyBookingsHistory: RecyclerView
    private lateinit var btnBrowse: Button

    override fun init() {
        super.init()

        presenter = MyBookingHistoryPresenter(RepositoryLocator
                        .getInstance(RemoteRepositoryLocator
                            .getInstance(RAGApi
                                .apiService(this)))
                        .bookingRepository)

    }


    override fun setupWidgets() {
        super.setupWidgets()

        rvMyBookingsHistory = findViewById(R.id.rv_my_bookings_history)
        btnBrowse = findViewById(R.id.btn_browse)

        btnBrowse.setOnClickListener(this)
    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> MyBookingHistoryItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvMyBookingsHistory.layoutManager = LinearLayoutManager(this)
        rvMyBookingsHistory.adapter = adapter
    }

    override fun showMyBookingsHistory(bookings: List<Booking>) {
        itemAdapter.set(bookings)
    }

    override fun navigateToBrowseProducts() {
        setResult(RES_BROWSE_PRODUCT)
        finish()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_browse -> presenter.onBtnBrowseClicked()
        }
    }

}
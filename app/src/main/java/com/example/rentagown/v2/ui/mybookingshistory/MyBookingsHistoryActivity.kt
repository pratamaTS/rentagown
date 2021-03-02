package com.example.rentagown.v2.ui.mybookingshistory

import android.content.Intent
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
import com.example.rentagown.v2.ui.bookingdetail.BookingDetailActivity
import com.example.rentagown.v2.ui.mybookingshistory.item.MyBookingHistoryItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

class MyBookingsHistoryActivity : BaseRAGActivity<MyBookingsHistoryConstract.Presenter>(), MyBookingsHistoryConstract.View,
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

    override lateinit var presenter: MyBookingsHistoryConstract.Presenter

    private lateinit var adapter: FastAdapter<MyBookingHistoryItem>
    private lateinit var itemAdapter: ModelAdapter<Booking, MyBookingHistoryItem>

    private lateinit var rvMyBookingsHistory: RecyclerView
    private lateinit var btnBrowse: Button

    override fun init() {
        super.init()

        presenter = MyBookingsHistoryPresenter(RepositoryLocator
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

        adapter.onClickListener = {
            _, _, item, _ ->
            presenter.onBookingHistoryItemClicked(item.model)
            false
        }
    }

    override fun showMyBookingsHistory(bookings: List<Booking>) {
        itemAdapter.set(bookings)
    }

    override fun navigateToBrowseProducts() {
        setResult(RES_BROWSE_PRODUCT)
        finish()
    }

    override fun navigateToBookingHistoryDetail(booking: Booking) {
        Intent(this, BookingDetailActivity::class.java).apply {
            putExtra("booking", booking)
            startActivityForResult(this, BookingDetailActivity.REQ_VIEW_BOOKING_DETAIL)
        }
    }

    override fun setDataBookingChanged(booking: Booking) {
        itemAdapter.adapterItems.find { i -> !i.model.transactionId.isNullOrBlank() && i.model.transactionId == booking.transactionId}?.apply {
            model = booking
            adapter.notifyItemChanged(itemAdapter.getAdapterPosition(this))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_browse -> presenter.onBtnBrowseClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == BookingDetailActivity.REQ_VIEW_BOOKING_DETAIL) {
            if(resultCode == BookingDetailActivity.RES_BOOKING_DETAIL_CHANGED) {
                presenter.onBookingDataChanged(data?.getParcelableExtra("booking"))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
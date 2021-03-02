package com.example.rentagown.v2.ui.home.mybookings

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.MainAfterActivity
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGFragment
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bookingdetail.BookingDetailActivity
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity
import com.example.rentagown.v2.ui.fittingsize.FittingSizeActivity
import com.example.rentagown.v2.ui.home.mybookings.item.MyBookingItem
import com.example.rentagown.v2.ui.mybookingshistory.MyBookingsHistoryActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.addClickListener

class MyBookingsFragment : BaseRAGFragment<MyBookingsContract.Presenter>(), MyBookingsContract.View,
        View.OnClickListener {

    companion object {

        fun newInstance(): MyBookingsFragment {
            return MyBookingsFragment()
        }

    }

    override val layoutId: Int = R.layout.fragment_my_bookings

    override var contentContainerId: Int = R.id.rv_my_bookings
    override var emptyPlaceholderId: Int = R.id.container_empty_data

    override val TAG: String = "MyBookingFragment"

    override lateinit var presenter: MyBookingsContract.Presenter

    private lateinit var adapter: FastAdapter<MyBookingItem>
    private lateinit var itemAdapter: ModelAdapter<Booking, MyBookingItem>

    private lateinit var rvMyBookings: RecyclerView
    private lateinit var btnBookingHistory: Button
    private lateinit var btnBrowse: Button

    override fun init() {
        super.init()

        presenter = MyBookingsPresenter(
            RepositoryLocator.getInstance(
                RemoteRepositoryLocator.getInstance(RAGApi.apiService(requireContext()))
            ).bookingRepository
        )

    }

    override fun setupWidgets(v: View) {
        super.setupWidgets(v)

        rvMyBookings = v.findViewById(R.id.rv_my_bookings)
        btnBookingHistory = v.findViewById(R.id.btn_booking_history)
        btnBrowse = v.findViewById(R.id.btn_browse)

        btnBookingHistory.setOnClickListener(this)
        btnBrowse.setOnClickListener(this)
    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> MyBookingItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvMyBookings.layoutManager = LinearLayoutManager(context)
        rvMyBookings.adapter = adapter


        adapter.onClickListener = { _, _, item, _ ->
            // later remove
            presenter.onItemClicked(item.model)
            false
        }

        adapter.addClickListener(resolveViews = { listOf() }, resolveView = { vh: MyBookingItem.ViewHolder -> vh.btnFittingSize }) {
            _, _, _, item ->
            presenter.onBtnFittingClicked(item.model)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_booking_history -> presenter.onBtnBookingHistoryClicked()
            R.id.btn_browse -> presenter.onBtnBrowserClicked()
        }

    }

    override fun showMyBookings(bookings: List<Booking>) {
        itemAdapter.set(bookings)
    }

    override fun navigateToBrowse() {
        activity?.let { mAct ->
            // sekarang langsung akses bottom nav dulu, bisa diubah nanti kalo sudah diperbaiki
            if(mAct is MainActivity) {
                mAct.setSelectedNavId(R.id.nav_home)
                return
            }
            if(mAct is MainAfterActivity) {
                mAct.setSelectedNavId(R.id.nav_home)
                return
            }
        }
    }

    override fun navigateToBookingHistory() {
        Intent(activity, MyBookingsHistoryActivity::class.java).apply {
            startActivityForResult(this, MyBookingsHistoryActivity.REQ_VIEW_BOOKINGS_HISTORY)
        }
    }

    override fun navigateToFitting(transactionId: String, fittingId: String?) {
        Intent(activity, FittingSizeActivity::class.java).apply {
            putExtra("transaction_id", transactionId)
            putExtra("fitting_id", fittingId)

            startActivityForResult(this, FittingSizeActivity.REQ_EDIT_FITTING)
        }
    }

    override fun updateFittingData(transactionId: String, fittingId: String?) {
        itemAdapter.adapterItems.find { i -> i.model.transactionId == transactionId }?.apply {
            model = model.copy(fittingId = fittingId)
        }
    }

    override fun navigateToConfirmPayment(booking: Booking) {
        Intent(activity, ConfirmPaymentActivity::class.java).apply {
            putExtra("booking", booking)
            startActivityForResult(this, ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT)
        }
    }

    override fun navigateToBookingDetail(booking: Booking) {
        Intent(activity, BookingDetailActivity::class.java).apply {
            putExtra("booking", booking)
            startActivityForResult(this, BookingDetailActivity.REQ_VIEW_BOOKING_DETAIL)
        }
    }

    override fun updateBookingData(booking: Booking) {
        itemAdapter.adapterItems.find { i -> !i.model.transactionId.isNullOrBlank() && i.model.transactionId == booking.transactionId}?.apply {
            model = booking
            adapter.notifyItemChanged(itemAdapter.getAdapterPosition(this))
        }
    }

    override fun removeBookingData(booking: Booking) {
        itemAdapter.adapterItems
                .indexOfFirst { i -> !i.model.transactionId.isNullOrBlank() && i.model.transactionId == booking.transactionId }
                .takeIf { idx -> idx > -1 }
                ?.apply {
                    itemAdapter.remove(this)
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == FittingSizeActivity.REQ_EDIT_FITTING) {
            if(resultCode == FittingSizeActivity.RES_FITTING_SAVED) {
                presenter.onFittingSaved(data?.getStringExtra("transaction_id"), data?.getStringExtra("fitting_id"))
            }
        } else if(requestCode == MyBookingsHistoryActivity.REQ_VIEW_BOOKINGS_HISTORY) {
            if(resultCode == MyBookingsHistoryActivity.RES_BROWSE_PRODUCT) {
                // nanti diubah kalo sudah jelas
                navigateToBrowse()
            }

        } else if(requestCode == ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT) {
            if(resultCode == ConfirmPaymentActivity.RES_CONFIRM_PAYMENT_SUCCESS) {
                // new booking data
                // nanti pindahkan ke tempat seharusnya
                presenter.onPaymentConfirmed(data?.getParcelableExtra("booking"))
            }
        } else if(requestCode == BookingDetailActivity.REQ_VIEW_BOOKING_DETAIL) {
            if(resultCode == BookingDetailActivity.RES_BOOKING_DETAIL_CHANGED) {
                presenter.onBookingDataChanged(data?.getParcelableExtra("booking"))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)

        }
    }
}

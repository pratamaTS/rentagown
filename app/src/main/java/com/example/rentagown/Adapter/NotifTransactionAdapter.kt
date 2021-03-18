package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NotifTransactionViewHolder
import com.example.rentagown.Model.NotifTransaction
import com.example.rentagown.R
import com.example.rentagown.Response.Notification.DataNotification
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotifTransactionAdapter(
    private val mContext: Context,
    private val notifTransactionList: ArrayList<DataNotification>
) :
    RecyclerView.Adapter<NotifTransactionViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifTransactionViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_notif_transaction, parent, false)
        return NotifTransactionViewHolder(v)
    }

    override fun onBindViewHolder(holder: NotifTransactionViewHolder, position: Int) {
        // Format Date
        val createdAtOld: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'").parse(notifTransactionList[position].createdAt)
        val startDateOld: Date = SimpleDateFormat("yyyy-MM-dd").parse(notifTransactionList[position].startDate)
        val startTimeOld: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(notifTransactionList[position].startDate)
        val createdAtNew = SimpleDateFormat("dd MMM yyyy").format(createdAtOld)
        val startDateNew = SimpleDateFormat("dd MMM yyyy").format(startDateOld)
        val startTimeNew = SimpleDateFormat("HH:mm:ss").format(startTimeOld)
        val createdTimeNew = SimpleDateFormat("HH:mm:ss").format(createdAtOld)

        holder.tvDateTransaction.setText(createdAtNew)
        holder.tvNameProduct.setText(notifTransactionList[position].productName?.capitalize()?.trim())
        holder.tvDateBooking.setText(createdAtNew)
        holder.tvTimeBooking.setText(createdTimeNew)
        holder.tvStatusPayment.setText(notifTransactionList[position].statusName)
        holder.tvPriceProduct.setText(numberFormat.format(notifTransactionList[position].productFinalPrice))
        holder.tvStatusNotif.setText(notifTransactionList[position].statusName)
    }

    override fun getItemCount(): Int {
        return notifTransactionList.size
    }
}
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
        holder.tvDateTransaction.setText(notifTransactionList[position].createdAt)
        holder.tvNameProduct.setText(notifTransactionList[position].productName)
        holder.tvDateBooking.setText(notifTransactionList[position].startDate)
        holder.tvTimeBooking.setText(notifTransactionList[position].startDate)
        holder.tvStatusPayment.setText(notifTransactionList[position].statusTransaction)
        holder.tvPriceProduct.setText(numberFormat.format(notifTransactionList[position].paidPrice))
        holder.tvStatusNotif.setText(notifTransactionList[position].statusTransaction)
    }

    override fun getItemCount(): Int {
        return notifTransactionList.size
    }
}
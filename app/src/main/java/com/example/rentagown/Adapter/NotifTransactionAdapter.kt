package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NotifTransactionViewHolder
import com.example.rentagown.Model.NotifTransaction
import com.example.rentagown.R

class NotifTransactionAdapter(
    private val mContext: Context,
    private val notifTransactionList: ArrayList<NotifTransaction>
) :
    RecyclerView.Adapter<NotifTransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifTransactionViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_notif_transaction, parent, false)
        return NotifTransactionViewHolder(v)
    }

    override fun onBindViewHolder(holder: NotifTransactionViewHolder, position: Int) {
        holder.tvDateTransaction.setText(notifTransactionList[position].dateTransaction)
        holder.tvNameProduct.setText(notifTransactionList[position].itemName)
        holder.tvDateBooking.setText(notifTransactionList[position].dateBook)
        holder.tvTimeBooking.setText(notifTransactionList[position].timeBook)
        holder.tvStatusPayment.setText(notifTransactionList[position].statusPayment)
        holder.tvPriceProduct.setText(notifTransactionList[position].price)
        holder.tvStatusNotif.setText(notifTransactionList[position].statusNotif)
    }

    override fun getItemCount(): Int {
        return notifTransactionList.size
    }
}
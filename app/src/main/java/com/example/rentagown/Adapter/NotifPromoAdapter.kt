package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NotifPromoViewHolder
import com.example.rentagown.R
import com.example.rentagown.Response.Notification.DataNotifPromo
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class NotifPromoAdapter(
    private val mContext: Context,
    private val notifPromoList: ArrayList<DataNotifPromo>
) :
    RecyclerView.Adapter<NotifPromoViewHolder>() {

    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifPromoViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_notif_promo, parent, false)
        return NotifPromoViewHolder(v)
    }

    override fun onBindViewHolder(holder: NotifPromoViewHolder, position: Int) {
//        holder.tvDatePromo.setText(notifPromoList[position].createdAt)
        val oldstring = notifPromoList[position].promoExp
        val xdate: Date = SimpleDateFormat("yyyy-MM-dd").parse(oldstring)
        val newstring = SimpleDateFormat("dd MMM yyyy").format(xdate)
        holder.tvDatePromo.setText(newstring)
        holder.tvNamePromo.setText(notifPromoList[position].promoName)
        holder.tvDiscountPromo.setText(notifPromoList[position].promoAmount.toString() + "%")
        holder.tvDescPromo.setText(notifPromoList[position].promoDesc)
    }

    override fun getItemCount(): Int {
        return notifPromoList.size
    }
}
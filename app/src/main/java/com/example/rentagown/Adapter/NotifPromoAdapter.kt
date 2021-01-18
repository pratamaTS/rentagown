package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NotifPromoViewHolder
import com.example.rentagown.Model.NotifPromo
import com.example.rentagown.R

class NotifPromoAdapter(
    private val mContext: Context,
    private val notifPromoList: ArrayList<NotifPromo>
) :
    RecyclerView.Adapter<NotifPromoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifPromoViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_notif_promo, parent, false)
        return NotifPromoViewHolder(v)
    }

    override fun onBindViewHolder(holder: NotifPromoViewHolder, position: Int) {
        holder.tvDatePromo.setText(notifPromoList[position].datePromo)
        holder.tvNamePromo.setText(notifPromoList[position].namePromo)
        holder.tvDiscountPromo.setText(notifPromoList[position].discountPromo)
        holder.tvDescPromo.setText(notifPromoList[position].descPromo)
    }

    override fun getItemCount(): Int {
        return notifPromoList.size
    }
}
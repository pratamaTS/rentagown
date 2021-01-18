package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class NotifPromoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvDatePromo: TextView
    var tvNamePromo: TextView
    var tvDiscountPromo: TextView
    var tvDescPromo: TextView

    init {
        tvDatePromo = itemView.findViewById(R.id.tv_date_promo_notif)
        tvNamePromo = itemView.findViewById(R.id.tv_name_promo_notif)
        tvDiscountPromo = itemView.findViewById(R.id.tv_discount_promo_notif)
        tvDescPromo = itemView.findViewById(R.id.tv_desc_promo_notif)
    }
}
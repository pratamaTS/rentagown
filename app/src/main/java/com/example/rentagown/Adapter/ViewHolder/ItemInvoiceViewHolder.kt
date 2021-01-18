package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class ItemInvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvItemName: TextView
    var tvPrice: TextView

    init {
        tvItemName = itemView.findViewById(R.id.tv_item_name_invoice)
        tvPrice = itemView.findViewById(R.id.tv_item_price_invoice)
    }
}
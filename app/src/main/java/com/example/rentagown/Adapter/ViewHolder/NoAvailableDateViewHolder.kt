package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class NoAvailableDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvStartDate: TextView
    var tvEndDate: TextView

    init {
        tvStartDate = itemView.findViewById(R.id.tv_start_date_not_available)
        tvEndDate = itemView.findViewById(R.id.tv_end_date_not_available)
    }
}
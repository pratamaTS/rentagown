package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NoAvailableDateViewHolder
import com.example.rentagown.Model.NoAvailableDate
import com.example.rentagown.R

class NoAvailableDateAdapter(
    private val mContext: Context,
    noAvailableDateList: ArrayList<NoAvailableDate>
) :
    RecyclerView.Adapter<NoAvailableDateViewHolder>() {
    private val noAvailableDateList: ArrayList<NoAvailableDate>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoAvailableDateViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_no_available_date, parent, false)
        return NoAvailableDateViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoAvailableDateViewHolder, position: Int) {
        holder.tvStartDate.setText(noAvailableDateList[position].startDateBooking)
        holder.tvEndDate.setText(noAvailableDateList[position].endDateBooking)
    }

    override fun getItemCount(): Int {
        return noAvailableDateList.size
    }

    init {
        this.noAvailableDateList = noAvailableDateList
    }
}
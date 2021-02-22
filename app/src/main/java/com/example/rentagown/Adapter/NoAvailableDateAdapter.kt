package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.NoAvailableDateViewHolder
import com.example.rentagown.Model.NoAvailableDate
import com.example.rentagown.R
import com.example.rentagown.Response.SeeUnDate.DataSeeUnDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoAvailableDateAdapter(
    private val mContext: Context,
    noAvailableDateList: ArrayList<DataSeeUnDate>
) :
    RecyclerView.Adapter<NoAvailableDateViewHolder>() {
    private val noAvailableDateList: ArrayList<DataSeeUnDate>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoAvailableDateViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_no_available_date, parent, false)
        return NoAvailableDateViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoAvailableDateViewHolder, position: Int) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val outputCheckFormat = SimpleDateFormat("dd MMMM YYYY")
        val startDateNew: Date = inputFormat.parse(noAvailableDateList[position].startDate)
        val endDateNew: Date = inputFormat.parse(noAvailableDateList[position].endDate)
        val startDateCheck: String = outputCheckFormat.format(startDateNew)
        val endDateCheck: String = outputCheckFormat.format(endDateNew)

        holder.tvStartDate.setText(startDateCheck)
        holder.tvEndDate.setText(endDateCheck)
    }

    override fun getItemCount(): Int {
        return noAvailableDateList.size
    }

    init {
        this.noAvailableDateList = noAvailableDateList
    }
}
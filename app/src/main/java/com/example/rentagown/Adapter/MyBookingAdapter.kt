package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.MyBookingViewHolder
import com.example.rentagown.Model.MyBooking
import com.example.rentagown.R

class MyBookingAdapter(private val mContext: Context, private val myBookingList: ArrayList<MyBooking>) :
    RecyclerView.Adapter<MyBookingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_booking, parent, false)
        return MyBookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyBookingViewHolder, position: Int) {
        holder.tvDateTransaction.setText(myBookingList[position].dateTransaction)
        holder.tvStartDateBooking.setText(myBookingList[position].startDateBooking)
        holder.tvEndDateBooking.setText(myBookingList[position].endDateBooking)
        holder.tvNameProduct.setText(myBookingList[position].nameProduct)
        holder.tvStatusBooking.setText(myBookingList[position].statusBooking)
        holder.tvPrice.setText(myBookingList[position].price)
        holder.tvDpPaid.setText(myBookingList[position].dpPaid)
        holder.tvRemainingBills.setText(myBookingList[position].remainingBills)
        holder.imProduct.setImageResource(myBookingList[position].imageProduct)
    }

    override fun getItemCount(): Int {
        return myBookingList.size
    }
}
package com.example.rentagown.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.MyBookingViewHolder
import com.example.rentagown.R
import com.example.rentagown.Response.MyBooking.DataMyBooking
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MyBookingAdapter(private val mContext: Context, private val myBookingList: DataMyBooking) :
    RecyclerView.Adapter<MyBookingViewHolder>() {

    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_booking, parent, false)
        return MyBookingViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyBookingViewHolder, position: Int) {
        val dateTransaction: LocalDateTime = LocalDateTime.parse(myBookingList.createdAt)
        val startDate: LocalDateTime = LocalDateTime.parse(myBookingList.startDate)
        val endDate: LocalDateTime = LocalDateTime.parse(myBookingList.endDate)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val dateTransactionNew: String = formatter.format(dateTransaction)
        val startDateNew: String = formatter.format(startDate)
        val endDateNew: String = formatter.format(endDate)

        holder.tvDateTransaction.setText(dateTransactionNew)
        holder.tvStartDateBooking.setText(startDateNew)
        holder.tvEndDateBooking.setText(endDateNew)
        holder.tvNameProduct.setText(myBookingList.productName)
        holder.tvStatusBooking.setText(myBookingList.statusBooking!!)
        holder.tvPrice.setText(numberFormat.format(myBookingList.paidPrice))
        holder.tvDpPaid.setText(numberFormat.format(myBookingList.downPayment))
        holder.tvRemainingBills.setText(myBookingList.remainingBills!!)

        if(myBookingList.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + myBookingList.pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}
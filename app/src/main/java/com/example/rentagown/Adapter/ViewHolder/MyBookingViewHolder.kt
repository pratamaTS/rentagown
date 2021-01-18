package com.example.rentagown.Adapter.ViewHolder

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.FittingSizeActivity
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.MyBooking
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class MyBookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var tvDateTransaction: TextView
    var tvStartDateBooking: TextView
    var tvEndDateBooking: TextView
    var tvNameProduct: TextView
    var tvPrice: TextView
    var tvDpPaid: TextView
    var tvRemainingBills: TextView
    var tvStatusBooking: TextView
    var imProduct: RoundedImageView
    var btnFitingSize: Button
    private val myBooking: MyBooking? = null
    private var itemClickListener: ItemClickListener? = null
    private var onClickListener: View.OnClickListener? = null

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_fitting_size -> {
                val fittingSize = Intent(itemView.context, FittingSizeActivity::class.java)
                itemView.context.startActivity(fittingSize)
            }
            else -> itemClickListener?.onClick(v, adapterPosition, false)
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        if (itemClickListener != null) {
            this.itemClickListener = itemClickListener
        }
    }

    fun setOnClickListener(onClickListener: View.OnClickListener?) {
        this.onClickListener = onClickListener
    }

    init {
        tvDateTransaction = itemView.findViewById(R.id.tv_tgl_transaction)
        tvStartDateBooking = itemView.findViewById(R.id.tv_start_date_booking_product)
        tvEndDateBooking = itemView.findViewById(R.id.tv_end_date_booking_product)
        tvNameProduct = itemView.findViewById(R.id.tv_name_dress_booking)
        tvPrice = itemView.findViewById(R.id.tv_price_booking)
        tvDpPaid = itemView.findViewById(R.id.tv_dp_paid)
        tvRemainingBills = itemView.findViewById(R.id.tv_remaining_bils)
        tvStatusBooking = itemView.findViewById(R.id.tv_status_booking)
        imProduct = itemView.findViewById(R.id.im_product_booking)
        btnFitingSize = itemView.findViewById(R.id.btn_fitting_size)
        itemView.setOnClickListener(this)
        btnFitingSize.setOnClickListener(this)
    }
}
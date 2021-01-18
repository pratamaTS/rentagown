package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class NotifTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvDateTransaction: TextView
    var tvNameProduct: TextView
    var tvDateBooking: TextView
    var tvTimeBooking: TextView
    var tvStatusPayment: TextView
    var tvPriceProduct: TextView
    var tvStatusNotif: TextView

    init {
        tvDateTransaction = itemView.findViewById(R.id.tv_date_transaction_notif)
        tvNameProduct = itemView.findViewById(R.id.tv_name_product_notif)
        tvDateBooking = itemView.findViewById(R.id.tv_date_booking_notif)
        tvTimeBooking = itemView.findViewById(R.id.tv_time_booking_notif)
        tvStatusPayment = itemView.findViewById(R.id.tv_status_payment_notif)
        tvPriceProduct = itemView.findViewById(R.id.tv_price_product_notif)
        tvStatusNotif = itemView.findViewById(R.id.tv_status_notif)
    }
}
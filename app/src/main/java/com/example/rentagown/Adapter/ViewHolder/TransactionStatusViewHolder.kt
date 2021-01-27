package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class TransactionStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvDateTransaction: TextView
    var tvNameProduct: TextView
    var tvTimeTrasaction: TextView
    var tvPaymentMethod: TextView
    var tvPriceTransaction: TextView
    var imProductTransaction: RoundedImageView

    init {
        tvDateTransaction = itemView.findViewById(R.id.tv_date_transaction_payment)
        tvNameProduct = itemView.findViewById(R.id.tv_name_dress_transaction)
        imProductTransaction = itemView.findViewById(R.id.im_product_transaction)
        tvTimeTrasaction = itemView.findViewById(R.id.tv_time_transaction)
        tvPaymentMethod = itemView.findViewById(R.id.tv_payment_method_transaction)
        tvPriceTransaction = itemView.findViewById(R.id.tv_total_price_transaction)
    }
}
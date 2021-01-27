package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.TransactionStatusViewHolder
import com.example.rentagown.Model.TransactionStatus
import com.example.rentagown.R

class TransactionStatusAdapter(private val mContext: Context, transactionStatusList: ArrayList<TransactionStatus>) : RecyclerView.Adapter<TransactionStatusViewHolder>() {
    private val transactionStatusList: ArrayList<TransactionStatus>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionStatusViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_transaction_status, parent, false)
        return TransactionStatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionStatusViewHolder, position: Int) {
        holder.tvDateTransaction.setText(transactionStatusList[position].dateTransaction)
        holder.imProductTransaction.setImageResource(transactionStatusList[position].imageProduct)
        holder.tvNameProduct.setText(transactionStatusList[position].nameProduct)
        holder.tvTimeTrasaction.setText(transactionStatusList[position].timeBTransaction)
        holder.tvPaymentMethod.setText(transactionStatusList[position].paymentMethod)
        holder.tvPriceTransaction.setText(transactionStatusList[position].priceTransaction)
    }

    override fun getItemCount(): Int {
        return transactionStatusList.size
    }

    init {
        this.transactionStatusList = transactionStatusList
    }
}
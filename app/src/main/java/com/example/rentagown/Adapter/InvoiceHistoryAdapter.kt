package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.InvoiceHistoryViewHolder
import com.example.rentagown.Model.InvoiceHistory
import com.example.rentagown.R

class InvoiceHistoryAdapter(
    private val invoiceHistoryList: ArrayList<InvoiceHistory>,
    var mContext: Context
) :
    RecyclerView.Adapter<InvoiceHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHistoryViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_invoice_history, parent, false)
        return InvoiceHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvoiceHistoryViewHolder, position: Int) {
        holder.tvIdInvoice.setText(invoiceHistoryList[position].idInvoice)
        holder.tvPriceInvoice.setText(invoiceHistoryList[position].priceInvoice)
        holder.tvStatusIncvoice.setText(invoiceHistoryList[position].statusInvoice)
        holder.tvTimeInvoice.setText(invoiceHistoryList[position].timeInvoice)
        holder.tvDateInvoice.setText(invoiceHistoryList[position].dateInvoice)
    }

    override fun getItemCount(): Int {
        return invoiceHistoryList.size
    }
}
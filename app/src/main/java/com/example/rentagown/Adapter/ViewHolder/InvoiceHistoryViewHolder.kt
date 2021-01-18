package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.InvoiceHistory
import com.example.rentagown.R

class InvoiceHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var tvIdInvoice: TextView
    var tvPriceInvoice: TextView
    var tvStatusIncvoice: TextView
    var tvTimeInvoice: TextView
    var tvDateInvoice: TextView
    private val invoiceHistory: InvoiceHistory? = null
    private val itemClickListener: ItemClickListener? = null
    private val onClickListener: View.OnClickListener? = null
    override fun onClick(v: View) {}

    init {
        tvIdInvoice = itemView.findViewById(R.id.tv_id_invoice_history)
        tvPriceInvoice = itemView.findViewById(R.id.tv_price_invoice_history)
        tvStatusIncvoice = itemView.findViewById(R.id.tv_status_invoice_history)
        tvTimeInvoice = itemView.findViewById(R.id.tv_time_invoice_history)
        tvDateInvoice = itemView.findViewById(R.id.tv_date_invoice_history)
        itemView.setOnClickListener(this)
    }
}
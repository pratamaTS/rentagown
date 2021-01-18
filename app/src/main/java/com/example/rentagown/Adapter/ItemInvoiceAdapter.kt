package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.ItemInvoiceViewHolder
import com.example.rentagown.Model.Invoice
import com.example.rentagown.R

class ItemInvoiceAdapter(private val mContext: Context, private val invoiceList: ArrayList<Invoice>) :
    RecyclerView.Adapter<ItemInvoiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInvoiceViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_invoice, parent, false)
        return ItemInvoiceViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemInvoiceViewHolder, position: Int) {
        holder.tvItemName.setText(invoiceList[position].itemName)
        holder.tvPrice.setText(invoiceList[position].price)
    }

    override fun getItemCount(): Int {
        return invoiceList.size
    }
}
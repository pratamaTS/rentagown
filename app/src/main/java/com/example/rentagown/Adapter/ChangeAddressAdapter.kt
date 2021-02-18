package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.ChangeAddressViewHolder
import com.example.rentagown.Model.ChangeAddress
import com.example.rentagown.R
import com.example.rentagown.Response.GetAddress.DataAddress

class ChangeAddressAdapter(
    private val changeAddressList: ArrayList<DataAddress>,
    var context: Context
) :
    RecyclerView.Adapter<ChangeAddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeAddressViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_address, parent, false)
        return ChangeAddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChangeAddressViewHolder, position: Int) {
        holder.tvLabelAddress.setText(changeAddressList[position].addressLabel)
        holder.tvName.setText(changeAddressList[position].name)
        holder.tvNoHp.setText(changeAddressList[position].phone)
        holder.tvDetailAddress.setText(changeAddressList[position].address)
    }

    override fun getItemCount(): Int {
        return changeAddressList.size
    }
}
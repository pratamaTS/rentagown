package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.ChangeAddressViewHolder
import com.example.rentagown.Model.ChangeAddress
import com.example.rentagown.R

class ChangeAddressAdapter(
    private val changeAddressList: ArrayList<ChangeAddress>,
    var context: Context
) :
    RecyclerView.Adapter<ChangeAddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeAddressViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_address, parent, false)
        return ChangeAddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChangeAddressViewHolder, position: Int) {
        holder.tvLabelAddress.setText(changeAddressList[position].labelAddress)
        holder.tvName.setText(changeAddressList[position].name)
        holder.tvNoHp.setText(changeAddressList[position].noHandphone)
        holder.tvDetailAddress.setText(changeAddressList[position].detailAddress)
    }

    override fun getItemCount(): Int {
        return changeAddressList.size
    }
}
package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SliderNewGownViewHolder
import com.example.rentagown.Model.NewGown
import com.example.rentagown.R

class SliderNewGownAdapter(private val mContext: Context, private val newGownList: ArrayList<NewGown>) :
    RecyclerView.Adapter<SliderNewGownViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderNewGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_slider, parent, false)
        return SliderNewGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderNewGownViewHolder, position: Int) {
        holder.tvItemName.setText(newGownList[position].itemName)
        holder.tvPrice.setText(newGownList[position].price)
        holder.imProduct.setImageResource(newGownList[position].image)
    }

    override fun getItemCount(): Int {
        return newGownList.size
    }
}
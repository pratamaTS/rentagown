package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SliderMainMenuViewHolder
import com.example.rentagown.Model.SliderMainMenu
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SliderMainMenuAdapter(
        private val mContext: Context,
        private val sliderMainMenuList: ArrayList<DataProduct>
) :
    RecyclerView.Adapter<SliderMainMenuViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderMainMenuViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_slider, parent, false)
        return SliderMainMenuViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderMainMenuViewHolder, position: Int) {
        holder.tvItemName.setText(sliderMainMenuList[position].productName)
        holder.tvPrice.setText(numberFormat.format(sliderMainMenuList[position].productPrice))
        holder.imContent.setImageResource(R.drawable.maternity_1)
    }

    override fun getItemCount(): Int {
        return sliderMainMenuList.size
    }

    fun replaceItems(mItems: ArrayList<DataProduct>) {
        sliderMainMenuList.clear()
        sliderMainMenuList.addAll(mItems)

        // notify semua atau satu2
        notifyDataSetChanged()
    }
}
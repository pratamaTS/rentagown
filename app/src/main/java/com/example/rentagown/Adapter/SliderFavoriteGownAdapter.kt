package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SliderFavoriteGownViewHolder
import com.example.rentagown.Model.FavoriteGown
import com.example.rentagown.R

class SliderFavoriteGownAdapter(
    private val mContext: Context,
    private val favoriteGownList: ArrayList<FavoriteGown>
) :
    RecyclerView.Adapter<SliderFavoriteGownViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderFavoriteGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_slider, parent, false)
        return SliderFavoriteGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderFavoriteGownViewHolder, position: Int) {
        holder.tvItemName.setText(favoriteGownList[position].itemName)
        holder.tvPrice.setText(favoriteGownList[position].price)
        holder.imProduct.setImageResource(favoriteGownList[position].image)
    }

    override fun getItemCount(): Int {
        return favoriteGownList.size
    }
}
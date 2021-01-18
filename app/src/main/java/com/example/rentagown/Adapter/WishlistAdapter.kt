package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.WishlistViewHolder
import com.example.rentagown.Model.Wishlist
import com.example.rentagown.R

class WishlistAdapter(private val mContext: Context, private val wishlistList: ArrayList<Wishlist>) :
    RecyclerView.Adapter<WishlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_product_wishlist, parent, false)
        return WishlistViewHolder(v)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.tvItemName.setText(wishlistList[position].itemName)
        holder.tvPrice.setText(wishlistList[position].price)
        holder.imProduct.setImageResource(wishlistList[position].image)
    }

    override fun getItemCount(): Int {
        return wishlistList.size
    }
}
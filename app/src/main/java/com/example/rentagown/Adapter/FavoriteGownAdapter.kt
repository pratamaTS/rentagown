package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.FavoriteGownViewHolder
import com.example.rentagown.Model.FavoriteGown
import com.example.rentagown.R

class FavoriteGownAdapter(
    private val mContext: Context,
    private val favoriteGownList: ArrayList<FavoriteGown>
) :
    RecyclerView.Adapter<FavoriteGownViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return FavoriteGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteGownViewHolder, position: Int) {
        holder.tvItemName.setText(favoriteGownList[position].itemName)
        holder.tvPrice.setText(favoriteGownList[position].price)
        holder.imProduct.setImageResource(favoriteGownList[position].image)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(v.context, "Product", Toast.LENGTH_SHORT).show()
            val product = Intent(mContext, ViewProductActivity::class.java)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return favoriteGownList.size
    }
}
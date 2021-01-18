package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SimilarCategoryViewHolder
import com.example.rentagown.Model.Product
import com.example.rentagown.R

class SimilarCategoryAdapter(
    private val mContext: Context,
    private val productList: ArrayList<Product>
) :
    RecyclerView.Adapter<SimilarCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarCategoryViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return SimilarCategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: SimilarCategoryViewHolder, position: Int) {
        holder.tvItemName.setText(productList[position].itemName)
        holder.tvPrice.setText(productList[position].price)
        holder.imProduct.setImageResource(productList[position].image)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.ProductViewHolder
import com.example.rentagown.Model.Product
import com.example.rentagown.R

class ProductAdapter(private val mContext: Context, private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvItemName.setText(productList[position].itemName)
        holder.tvPrice.setText(productList[position].price)
        holder.imProduct.setImageResource(productList[position].image)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(v.context, "Product", Toast.LENGTH_SHORT).show()
            val product = Intent(mContext, ViewProductActivity::class.java)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun replaceItems(mItems: List<Product>?) {
        productList.clear()
        productList.addAll(mItems!!)

        //notify semua atau satu2
        notifyDataSetChanged()
    }
}
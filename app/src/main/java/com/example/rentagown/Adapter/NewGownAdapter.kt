package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.NewGownViewHolder
import com.example.rentagown.Model.NewGown
import com.example.rentagown.R

class NewGownAdapter(private val mContext: Context, private val newGownList: ArrayList<NewGown>) :
    RecyclerView.Adapter<NewGownViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return NewGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewGownViewHolder, position: Int) {
        holder.tvItemName.setText(newGownList[position].itemName)
        holder.tvPrice.setText(newGownList[position].price)
        holder.imProduct.setImageResource(newGownList[position].image)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(v.context, "Product", Toast.LENGTH_SHORT).show()
            val product = Intent(mContext, ViewProductActivity::class.java)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return newGownList.size
    }
}
package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.CartViewHolder
import com.example.rentagown.Model.Cart
import com.example.rentagown.R

class CartAdapter(private val mContext: Context, private val cartList: ArrayList<Cart>) :
    RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cart, parent, false)
        return CartViewHolder(v)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.imProduct.setImageResource(cartList[position].image)
        holder.tvItemName.setText(cartList[position].itemName)
        holder.tvCategoryGown.setText(cartList[position].categoryGown)
        holder.tvSpecialTreatment.setText(cartList[position].specialTreatment)
        holder.tvPrice.setText(cartList[position].price)
        holder.tvDateBooking.setText(cartList[position].dateBooking)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}
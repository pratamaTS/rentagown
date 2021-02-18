package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.WishlistViewHolder
import com.example.rentagown.Model.Wishlist
import com.example.rentagown.R
import com.example.rentagown.Response.GetWishlist.DataWishlist
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class WishlistAdapter(private val mContext: Context, private val wishlistList: ArrayList<DataWishlist>) :
    RecyclerView.Adapter<WishlistViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_product_wishlist, parent, false)
        return WishlistViewHolder(v)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.tvItemName.setText(wishlistList[position].productName)

        if(wishlistList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(wishlistList[position].finalPrice))
            holder.tvDiscount.setText(wishlistList[position].promoAmount.toString() + "%")
            holder.tvPriceOld.setText(numberFormat.format(wishlistList[position].productPrice))
        }else{
            holder.tvPriceOld.visibility = View.INVISIBLE
            holder.tvDiscount.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(wishlistList[position].productPrice))
        }

        if(wishlistList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + wishlistList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ViewProductActivity::class.java)
            product.putExtra("id_product", wishlistList[position].idProduct)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return wishlistList.size
    }
}
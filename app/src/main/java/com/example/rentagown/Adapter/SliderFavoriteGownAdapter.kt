package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.SliderFavoriteGownViewHolder
import com.example.rentagown.Model.FavoriteGown
import com.example.rentagown.R
import com.example.rentagown.Response.FavoriteGown.DataFavoriteGown
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SliderFavoriteGownAdapter(
    private val favoriteGownList: ArrayList<DataFavoriteGown>
) :
    RecyclerView.Adapter<SliderFavoriteGownViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderFavoriteGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_slider, parent, false)
        return SliderFavoriteGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderFavoriteGownViewHolder, position: Int) {
        holder.tvItemName.setText(favoriteGownList[position].productName?.capitalize()?.trimEnd())

        if(favoriteGownList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(favoriteGownList[position].finalPrice))
            holder.tvDisc.setText(favoriteGownList[position].promoAmount.toString() + "%")
            holder.tvOldPrice.setText(numberFormat.format(favoriteGownList[position].productPrice))
        }else{
            holder.tvOldPrice.visibility = View.INVISIBLE
            holder.tvDisc.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(favoriteGownList[position].productPrice))
        }

        if(favoriteGownList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + favoriteGownList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener { v ->
            val product = Intent(v.context, ProductDetailActivity::class.java)
            product.putExtra("id_product", favoriteGownList[position].idProduct)
            product.putExtra("product_id", favoriteGownList[position].idProduct)
            product.putExtra("category", favoriteGownList[position].nameProductCategory)
            v.context.startActivity(product)
        }

        holder.btnBooking.setOnClickListener{ v ->
            val product = Intent(v.context, ProductDetailActivity::class.java)
            product.putExtra("id_product", favoriteGownList[position].idProduct)
            product.putExtra("product_id", favoriteGownList[position].idProduct)
            product.putExtra("category", favoriteGownList[position].nameProductCategory)
            v.context.startActivity(product)
        }
    }

    override fun getItemCount(): Int {
        return favoriteGownList.size
    }
}
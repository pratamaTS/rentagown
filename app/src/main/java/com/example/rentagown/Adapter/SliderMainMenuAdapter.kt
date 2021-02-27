package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.SliderMainMenuViewHolder
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class SliderMainMenuAdapter(
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
        holder.tvItemName.setText(sliderMainMenuList[position].productName?.capitalize()?.trimEnd())
        holder.tvPrice.setText(numberFormat.format(sliderMainMenuList[position].productPrice))

        val imgURL: String = "http://absdigital.id:5000" + sliderMainMenuList[position].pathPhoto
        Picasso.get().load(imgURL).into(holder.imContent)

        holder.itemView.setOnClickListener { v ->
            val product = Intent(v.context, ProductDetailActivity::class.java)
            product.putExtra("id_product", sliderMainMenuList[position].idProduct)
            product.putExtra("product_id", sliderMainMenuList[position].idProduct)
            product.putExtra("category", sliderMainMenuList[position].nameProductCategory)
            v.context.startActivity(product)
        }
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
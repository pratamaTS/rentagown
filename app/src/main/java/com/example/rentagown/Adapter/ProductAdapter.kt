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
import com.example.rentagown.Response.Product.DataProduct
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(private val mContext: Context, private val productList: ArrayList<DataProduct>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvItemName.setText(productList[position].productName?.capitalize()?.trim())

        if(productList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(productList[position].finalPrice))
            holder.tvDiscount.setText(productList[position].promoAmount.toString() + "%")
            holder.tvPriceOld.setText(numberFormat.format(productList[position].productPrice))
        }else{
            holder.tvPriceOld.visibility = View.INVISIBLE
            holder.tvDiscount.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(productList[position].productPrice))
        }

        if(productList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + productList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ViewProductActivity::class.java)
            product.putExtra("id_product", productList[position].idProduct)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun replaceItems(mItems: ArrayList<DataProduct>?) {
        productList.clear()
        productList.addAll(mItems!!)

        //notify semua atau satu2
        notifyDataSetChanged()
    }
}
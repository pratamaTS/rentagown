package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SimilarCategoryViewHolder
import com.example.rentagown.Model.Product
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.Response.ProductCategory.DataProductCategory
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SimilarCategoryAdapter(
    private val mContext: Context,
    private val productList: ArrayList<DataProduct>
) :
    RecyclerView.Adapter<SimilarCategoryViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarCategoryViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return SimilarCategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: SimilarCategoryViewHolder, position: Int) {
        holder.tvItemName.setText(productList[position].productName?.capitalize()?.trimEnd())

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
            val imgURL: String = "http://absdigital.id:55000" + productList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
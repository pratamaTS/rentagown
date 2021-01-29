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
import com.example.rentagown.Response.NewGown.DataNewGown
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class NewGownAdapter(private val mContext: Context, private val newGownList: ArrayList<DataNewGown>) :
    RecyclerView.Adapter<NewGownViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return NewGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewGownViewHolder, position: Int) {
        holder.tvItemName.setText(newGownList[position].productName?.capitalize()?.trim())

        if(newGownList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(newGownList[position].finalPrice))
            holder.tvDiscount.setText(newGownList[position].promoAmount.toString() + "%")
            holder.tvPriceOld.setText(numberFormat.format(newGownList[position].productPrice))
        }else{
            holder.tvPriceOld.visibility = View.INVISIBLE
            holder.tvDiscount.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(newGownList[position].productPrice))
        }

        if(newGownList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + newGownList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ViewProductActivity::class.java)
            product.putExtra("id_product", newGownList[position].idProduct)
            mContext.startActivity(product)
        })
    }

    override fun getItemCount(): Int {
        return newGownList.size
    }
}
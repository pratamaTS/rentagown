package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.SliderNewGownViewHolder
import com.example.rentagown.R
import com.example.rentagown.Response.NewGown.DataNewGown
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL
import java.text.NumberFormat
import java.util.*


class SliderNewGownAdapter(
    private val mContext: Context,
    private val newGownList: ArrayList<DataNewGown>
) :
    RecyclerView.Adapter<SliderNewGownViewHolder>() {

    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderNewGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_slider, parent, false)
        return SliderNewGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderNewGownViewHolder, position: Int) {
        holder.tvItemName.setText(newGownList[position].productName?.capitalize()?.trimEnd())

        if(newGownList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(newGownList[position].finalPrice))
            holder.tvDisc.setText(newGownList[position].promoAmount.toString() + "%")
            holder.tvOldPrice.setText(numberFormat.format(newGownList[position].productPrice))
        }else{
            holder.tvOldPrice.visibility = View.INVISIBLE
            holder.tvDisc.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(newGownList[position].productPrice))
        }

        if(newGownList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + newGownList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener { v ->
            val product = Intent(v.context, ViewProductActivity::class.java)
            product.putExtra("id_product", newGownList[position].idProduct)
            v.context.startActivity(product)
        }
    }

    override fun getItemCount(): Int {
        return newGownList.size
    }
}
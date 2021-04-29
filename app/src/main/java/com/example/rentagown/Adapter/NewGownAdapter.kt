package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.ViewHolder.NewGownViewHolder
import com.example.rentagown.BuildConfig
import com.example.rentagown.Model.NewGown
import com.example.rentagown.R
import com.example.rentagown.Response.NewGown.DataNewGown
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.example.rentagown.v2.util.Utils
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
        holder.tvPromoName.text = newGownList[position].promoName?.trim()

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
            Glide.with(holder.itemView.context)
                    .load(BuildConfig.BASE_PHOTO_URL + newGownList[position].pathPhoto)
                    .listener(Utils.getGlideException())
                    .fitCenter()
                    .error(R.color.colorGray)
                    .into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", newGownList[position].idProduct)
            product.putExtra("product_id", newGownList[position].idProduct)
            product.putExtra("category", newGownList[position].nameProductCategory)
            mContext.startActivity(product)
        })

        holder.btnBooking.setOnClickListener{ v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", newGownList[position].idProduct)
            product.putExtra("product_id", newGownList[position].idProduct)
            product.putExtra("category", newGownList[position].nameProductCategory)
            mContext.startActivity(product)
        }
    }

    override fun getItemCount(): Int {
        return newGownList.size
    }
}
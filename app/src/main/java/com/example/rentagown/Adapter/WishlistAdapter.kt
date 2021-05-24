package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.Adapter.ViewHolder.WishlistViewHolder
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.Response.GetWishlist.DataWishlist
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.example.rentagown.v2.util.Utils
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class WishlistAdapter(private val mContext: Context, private val wishlistList: ArrayList<DataWishlist>) :
    RecyclerView.Adapter<WishlistViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_product, parent, false)
        return WishlistViewHolder(v)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.tvItemName.setText(wishlistList[position].productName)

        if(wishlistList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPriceOld.visibility = View.VISIBLE
            holder.tvDiscount.visibility = View.VISIBLE
            holder.tvPromoName.visibility = View.VISIBLE

            holder.tvPromoName.text = wishlistList[position].promoName
            holder.tvPrice.setText(numberFormat.format(wishlistList[position].finalPrice))
            holder.tvDiscount.setText(wishlistList[position].promoAmount.toString() + "%")
            holder.tvPriceOld.setText(numberFormat.format(wishlistList[position].productPrice))
        }else{
            holder.tvPriceOld.visibility = View.INVISIBLE
            holder.tvDiscount.visibility = View.INVISIBLE
            holder.tvPromoName.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(wishlistList[position].productPrice))
        }

        if(wishlistList[position].pathPhoto?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                    .load(BuildConfig.BASE_PHOTO_URL + wishlistList[position].pathPhoto)
                    .listener(Utils.getGlideException())
                    .fitCenter()
                    .error(R.color.colorGray)
                    .into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", wishlistList[position].idProduct)
            product.putExtra("product_id", wishlistList[position].idProduct)
            product.putExtra("category", wishlistList[position].nameProductCategory)
            mContext.startActivity(product)
        })

        holder.btnBooking.setOnClickListener{ v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", wishlistList[position].idProduct)
            product.putExtra("product_id", wishlistList[position].idProduct)
            product.putExtra("category", wishlistList[position].nameProductCategory)
            mContext.startActivity(product)
        }
    }

    override fun getItemCount(): Int {
        return wishlistList.size
    }
}
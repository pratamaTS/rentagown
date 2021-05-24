package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.Adapter.ViewHolder.FavoriteGownViewHolder
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.Response.FavoriteGown.DataFavoriteGown
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.example.rentagown.v2.util.Utils
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class FavoriteGownAdapter(
    private val mContext: Context,
    private val favoriteGownList: ArrayList<DataFavoriteGown>
) :
    RecyclerView.Adapter<FavoriteGownViewHolder>() {
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGownViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return FavoriteGownViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteGownViewHolder, position: Int) {
        holder.tvItemName.setText(favoriteGownList[position].productName?.capitalize()?.trim())

        if(favoriteGownList[position].idPromo?.isNotEmpty() == true) {
            holder.tvPrice.setText(numberFormat.format(favoriteGownList[position].finalPrice))
            holder.tvDiscount.setText(favoriteGownList[position].promoAmount.toString() + "%")
            holder.tvPriceOld.setText(numberFormat.format(favoriteGownList[position].productPrice))
        }else{
            holder.tvPriceOld.visibility = View.INVISIBLE
            holder.tvDiscount.visibility = View.INVISIBLE
            holder.tvPrice.setText(numberFormat.format(favoriteGownList[position].productPrice))
        }

        if(favoriteGownList[position].pathPhoto?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                    .load(BuildConfig.BASE_PHOTO_URL + favoriteGownList[position].pathPhoto)
                    .listener(Utils.getGlideException())
                    .fitCenter()
                    .error(R.color.colorGray)
                    .into(holder.imProduct)
        }else {
            holder.imProduct.setImageResource(R.drawable.family_1)
        }

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", favoriteGownList[position].idProduct)
            product.putExtra("product_id", favoriteGownList[position].idProduct)
            product.putExtra("category", favoriteGownList[position].nameProductCategory)
            mContext.startActivity(product)
        })

        holder.btnBooking.setOnClickListener{ v ->
            val product = Intent(mContext, ProductDetailActivity::class.java)
            product.putExtra("id_product", favoriteGownList[position].idProduct)
            product.putExtra("product_id", favoriteGownList[position].idProduct)
            product.putExtra("category", favoriteGownList[position].nameProductCategory)
            mContext.startActivity(product)
        }
    }

    override fun getItemCount(): Int {
        return favoriteGownList.size
    }
}
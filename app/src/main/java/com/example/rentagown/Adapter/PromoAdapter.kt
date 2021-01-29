package com.example.rentagown.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.DetailPromoActivity
import com.example.rentagown.Adapter.ViewHolder.PromoViewHolder
import com.example.rentagown.Model.Promo
import com.example.rentagown.R
import com.example.rentagown.Response.Promo.DataPromo
import com.squareup.picasso.Picasso

class PromoAdapter(private val mContext: Context, private val promoList: ArrayList<DataPromo>) :
    RecyclerView.Adapter<PromoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promo_list, parent, false)
        return PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        if(promoList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + promoList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imPromoList)
        }else {
            holder.imPromoList.setImageResource(R.drawable.promo)
        }

        holder.tvTitlePromoList.setText(promoList[position].promoName?.capitalize()?.trimEnd())
        holder.tvDescPromoList.setText("The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), \" +\n" +
                "                \"Booking period until 15 September 2020. Rental period until December 2021.")

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val detailPromo = Intent(mContext, DetailPromoActivity::class.java)
            detailPromo.putExtra("id_promo", promoList[position].idPromo)
            mContext.startActivity(detailPromo)
        })
    }

    override fun getItemCount(): Int {
        return promoList.size
    }
}
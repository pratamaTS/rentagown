package com.example.rentagown.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.DetailPromoActivity
import com.example.rentagown.Adapter.ViewHolder.SliderPromoViewHolder
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.Response.Promo.DataPromo
import com.squareup.picasso.Picasso

class SliderPromoAdapter(private val promoList: ArrayList<DataPromo>) :
    RecyclerView.Adapter<SliderPromoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderPromoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promo_slider, parent, false)
        return SliderPromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderPromoViewHolder, position: Int) {
        if(promoList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = BuildConfig.BASE_PHOTO_URL + promoList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imPromo)
        }

        holder.tvTitlePromo.setText(promoList[position].promoName?.capitalize()?.trimEnd())
        holder.tvDescPromo.setText(promoList[position].promoDesc)

        holder.itemView.setOnClickListener { v ->
            val promo = Intent(v.context, DetailPromoActivity::class.java)
            promo.putExtra("id_promo", promoList[position].idPromo)
            v.context.startActivity(promo)
        }
    }

    override fun getItemCount(): Int {
        return promoList.size
    }
}
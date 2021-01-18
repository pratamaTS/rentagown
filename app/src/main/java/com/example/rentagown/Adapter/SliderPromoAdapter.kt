package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.SliderPromoViewHolder
import com.example.rentagown.Model.Promo
import com.example.rentagown.R

class SliderPromoAdapter(private val mContext: Context, private val promoList: ArrayList<Promo>) :
    RecyclerView.Adapter<SliderPromoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderPromoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promo_slider, parent, false)
        return SliderPromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderPromoViewHolder, position: Int) {
        holder.imPromo.setImageResource(promoList[position].imPromo)
        holder.tvTitlePromo.setText(promoList[position].title)
        holder.tvDescPromo.setText(promoList[position].desc)
    }

    override fun getItemCount(): Int {
        return promoList.size
    }
}
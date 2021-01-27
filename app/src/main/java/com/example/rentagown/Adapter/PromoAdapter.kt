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

class PromoAdapter(private val mContext: Context, private val promoList: ArrayList<Promo>) :
    RecyclerView.Adapter<PromoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promo_list, parent, false)
        return PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.imPromoList.setImageResource(promoList[position].imPromo)
        holder.tvTitlePromoList.setText(promoList[position].title)
        holder.tvDescPromoList.setText(promoList[position].desc)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(v.context, "Product", Toast.LENGTH_SHORT).show()
            val detailPromo = Intent(mContext, DetailPromoActivity::class.java)
            mContext.startActivity(detailPromo)
        })
    }

    override fun getItemCount(): Int {
        return promoList.size
    }
}
package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class SliderPromoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitlePromo: TextView
    var tvDescPromo: TextView
    var imPromo: RoundedImageView

    init {
        imPromo = itemView.findViewById(R.id.im_item_promo)
        tvTitlePromo = itemView.findViewById(R.id.tv_title_item_promo)
        tvDescPromo = itemView.findViewById(R.id.tv_desc_item_promo)
    }
}
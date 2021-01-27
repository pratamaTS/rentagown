package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class PromoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitlePromoList: TextView
    var tvDescPromoList: TextView
    var imPromoList: RoundedImageView

    init {
        imPromoList = itemView.findViewById(R.id.im_item_list_promo)
        tvTitlePromoList = itemView.findViewById(R.id.tv_title_item_list_promo)
        tvDescPromoList = itemView.findViewById(R.id.tv_desc_item_list_promo)
    }
}
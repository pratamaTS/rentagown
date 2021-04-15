package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Model.SliderMainMenu
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class SliderMainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvItemName: TextView
    var tvPrice: TextView
    var tvOldPrice: TextView
    var tvDisc: TextView
    var imContent: RoundedImageView
    private val sliderMainMenu: SliderMainMenu? = null

    init {
        tvItemName = itemView.findViewById(R.id.tv_name_dress_main)
        tvPrice = itemView.findViewById(R.id.tv_harga_dress_main)
        tvOldPrice = itemView.findViewById(R.id.tv_price_old_dress_main)
        tvDisc = itemView.findViewById(R.id.tv_discount_main)
        imContent = itemView.findViewById(R.id.im_content_main)
    }
}
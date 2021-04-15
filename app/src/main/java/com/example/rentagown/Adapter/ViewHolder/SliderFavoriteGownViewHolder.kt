package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class SliderFavoriteGownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvItemName: TextView
    var tvPromoName: TextView
    var tvPrice: TextView
    var tvOldPrice: TextView
    var tvDisc: TextView
    var imProduct: RoundedImageView
    var btnBooking: Button

    init {
        tvItemName = itemView.findViewById(R.id.tv_name_dress_product)
        tvPromoName = itemView.findViewById(R.id.tv_label_promo_product)
        tvPrice = itemView.findViewById(R.id.tv_price_dress_product)
        tvOldPrice = itemView.findViewById(R.id.tv_price_old_dress_product)
        tvDisc = itemView.findViewById(R.id.tv_discount_product)
        imProduct = itemView.findViewById(R.id.im_image_product)
        btnBooking = itemView.findViewById(R.id.btn_booking_now)
    }
}
package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imProduct: RoundedImageView
    var tvItemName: TextView
    var tvCategoryGown: TextView
    var tvSpecialTreatment: TextView
    var tvPrice: TextView
    var tvDateBooking: TextView
    var btnQty: ElegantNumberButton

    init {
        imProduct = itemView.findViewById(R.id.im_product_cart)
        tvItemName = itemView.findViewById(R.id.tv_name_dress_cart)
        tvCategoryGown = itemView.findViewById(R.id.tv_category_gown_cart)
        tvSpecialTreatment = itemView.findViewById(R.id.tv_special_treatment_cart)
        tvPrice = itemView.findViewById(R.id.tv_price_dress_cart)
        tvDateBooking = itemView.findViewById(R.id.tv_date_booking_cart)
        btnQty = itemView.findViewById(R.id.btn_qty)
    }
}
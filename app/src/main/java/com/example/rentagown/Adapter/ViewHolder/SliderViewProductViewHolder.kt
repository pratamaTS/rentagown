package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.ImageView
import com.example.rentagown.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderViewProductViewHolder(itemView: View) :
    SliderViewAdapter.ViewHolder(itemView) {
    var imageSlider: ImageView

    init {
        imageSlider = itemView.findViewById(R.id.img_product)
    }
}

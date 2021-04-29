package com.example.rentagown.v2.ui.productdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.Model.SliderItemProduct
import com.example.rentagown.R
import com.example.rentagown.v2.util.Utils
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderViewFullScreenAdapter(private var imagesPath: ArrayList<String?>) : SliderViewAdapter<SliderViewFullScreenAdapter.ViewHolder>() {

    class ViewHolder(view: View) : SliderViewAdapter.ViewHolder(view) {
        var ivProduct: ImageView = view.findViewById(R.id.full_img)
    }

    override fun getCount() = imagesPath.size


    override fun onCreateViewHolder(parent: ViewGroup): SliderViewFullScreenAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slider_fullscreen, parent, false)
        return SliderViewFullScreenAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewFullScreenAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(BuildConfig.BASE_PHOTO_URL + imagesPath[position])
            .listener(Utils.getGlideException())
            .fitCenter()
            .error(R.color.colorGray)
            .listener(Utils.getGlideException())
            .into(holder.ivProduct)
    }

    fun replaceData(mImagesPath: List<String?>) {
        imagesPath.clear()
        imagesPath.addAll(mImagesPath)
        notifyDataSetChanged()
    }
}
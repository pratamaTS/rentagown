package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rentagown.Adapter.ViewHolder.SliderViewProductViewHolder
import com.example.rentagown.BuildConfig
import com.example.rentagown.Model.SliderItemProduct
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataDetailProduct
import com.example.rentagown.Response.Product.DataPhoto
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class SliderViewProductAdapter(
    private val context: Context,
    private var mSliderItems: ArrayList<DataPhoto>
) :
    SliderViewAdapter<SliderViewProductViewHolder>() {
    fun renewItems(sliderItems: ArrayList<DataPhoto>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: DataPhoto) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewProductViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slider_product, null)
        return SliderViewProductViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderViewProductViewHolder, position: Int) {
//        SliderItemProduct sliderItemProduct = mSliderItems.get(position);
        val imgURL: String = BuildConfig.BASE_PHOTO_URL + mSliderItems[position].pathPhoto
        Picasso.get().load(imgURL).into(viewHolder.imageSlider)
//        Glide.with(viewHolder.itemView)
//                .load(sliderItemProduct.getImageProduct())
//                .fitCenter()
//                .into(viewHolder.imageSlider);
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }
}
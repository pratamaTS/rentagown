package com.example.rentagown.v2.ui.productdetail.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.ui.productdetail.viewphoto.FullScreenActivity
import com.example.rentagown.v2.util.Utils
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductImageAdapter(private var context: Context, private var idProduct: String, private var imagesPath: ArrayList<String?>) : SliderViewAdapter<ProductImageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : SliderViewAdapter.ViewHolder(view) {
        var ivProduct: ImageView = view.findViewById(R.id.iv_product)
    }

    override fun getCount() = imagesPath.size


    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product_image_v2,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(BuildConfig.BASE_PHOTO_URL + imagesPath[position])
            .listener(Utils.getGlideException())
            .centerCrop()
            .error(R.color.colorGray)
            .listener(Utils.getGlideException())
            .into(holder.ivProduct)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val fullscreen = Intent(context, FullScreenActivity::class.java)
            fullscreen.putExtra("product_id", idProduct)
            context.startActivity(fullscreen)
        })
    }

    fun replaceData(mImagesPath: List<String?>) {
        imagesPath.clear()
        imagesPath.addAll(mImagesPath)
        notifyDataSetChanged()
    }

}
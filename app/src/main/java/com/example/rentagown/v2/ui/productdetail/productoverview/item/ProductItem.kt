package com.example.rentagown.v2.ui.productdetail.productoverview.item

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.util.Utils
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class ProductItem(model: Product) : ModelAbstractItem<Product, ProductItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_product_v2
    override val type: Int = R.id.item_product

    override var identifier: Long = if(model.id.isNullOrBlank()) -1 else model.id.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        Glide.with(holder.itemView.context)
            .load(BuildConfig.BASE_PHOTO_URL + model.photoPath)
            .listener(Utils.getGlideException())
            .centerCrop()
            .error(R.color.colorGray)
            .into(holder.ivProduct)

        val mModel = model

        holder.tvDiscountAmount.visibility = if(mModel.promoAmount != null && mModel.promoAmount > 0) View.VISIBLE else View.INVISIBLE
        holder.tvPrice.visibility = if(mModel.promoAmount != null && mModel.promoAmount > 0) View.VISIBLE else View.INVISIBLE

        holder.tvProductName.text = mModel.productName
        holder.tvFinalPrice.text = Utils.formatMoney(mModel.finalPrice)
        holder.tvPrice.text = Utils.formatMoney(mModel.productPrice)
        holder.tvDiscountAmount.text = mModel.promoAmount.toString() + holder.itemView.context.getString(R.string.sym_discount_amount)
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.ivProduct.setImageDrawable(null)

        holder.tvProductName.text = null
        holder.tvFinalPrice.text = null
        holder.tvPrice.text = null
        holder.tvDiscountAmount.text = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivProduct: ImageView = view.findViewById(R.id.iv_product)
        var tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        var tvFinalPrice: TextView = view.findViewById(R.id.tv_final_price)
        var tvPrice: TextView = view.findViewById(R.id.tv_price)
        var tvDiscountAmount: TextView = view.findViewById(R.id.tv_discount_amount)
        var btnBookingNow: Button = view.findViewById(R.id.btn_booking_now)

    }

}
package com.example.rentagown.v2.ui.productdetail.productreviews.item

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class ProductReviewItem(model: ProductReview) : ModelAbstractItem<ProductReview, ProductReviewItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_product_review_v2
    override val type: Int = R.id.item_product_review
    override var identifier: Long = if(model.ratingId.isNullOrBlank()) -1 else model.ratingId.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        holder.tvUserName.text = model.name
        holder.rbTransactionRatingStar.rating = model.reviewStar ?: 0f
        holder.tvTransactionRatingStar.text = (model.reviewStar ?: 0f).toString()
        holder.tvReviewTime.text = model.reviewDateTime
        holder.tvReviewComment.text = model.reviewComment
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.tvUserName.text = null
        holder.rbTransactionRatingStar.rating = 0f
        holder.tvTransactionRatingStar.text = null
        holder.tvReviewTime.text = null
        holder.tvReviewComment.text = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvUserName: TextView = view.findViewById(R.id.tv_user_name)
        var rbTransactionRatingStar: RatingBar = view.findViewById(R.id.rb_transaction_rating_star)
        var tvTransactionRatingStar: TextView = view.findViewById(R.id.tv_transaction_rating_star)
        var tvReviewTime: TextView = view.findViewById(R.id.tv_review_time)
        var tvReviewComment: TextView = view.findViewById(R.id.tv_review_comment)
    }


}
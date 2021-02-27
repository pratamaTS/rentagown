package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNameUserReview: TextView
    var tvRatingsReview: TextView
    var tvTimeReview: TextView
    var tvCommentReview: TextView

    init {
        tvNameUserReview = itemView.findViewById(R.id.tv_name_user_review)
        tvRatingsReview = itemView.findViewById(R.id.tv_ratings_review)
        tvTimeReview = itemView.findViewById(R.id.tv_review_time)
        tvCommentReview = itemView.findViewById(R.id.tv_review_comment)
    }
}
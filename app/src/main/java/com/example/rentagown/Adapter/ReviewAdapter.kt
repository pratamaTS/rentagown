package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.ReviewViewHolder
import com.example.rentagown.Model.Review
import com.example.rentagown.R

class ReviewAdapter(private val mContext: Context, private val reviewList: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_review, parent, false)
        return ReviewViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.tvNameUserReview.setText(reviewList[position].nameUser)
        holder.tvRatingsReview.setText(reviewList[position].ratings)
        holder.tvTimeReview.setText(reviewList[position].timeReview)
        holder.tvCommentReview.setText(reviewList[position].commentReview)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}
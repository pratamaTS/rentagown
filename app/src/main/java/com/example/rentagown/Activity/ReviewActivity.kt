package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ReviewAdapter
import com.example.rentagown.Model.Review
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class ReviewActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvReview: RecyclerView? = null
    var reviewList: ArrayList<Review> = ArrayList()
    var reviewsAdapter: ReviewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvReview = findViewById(R.id.rv_comment_review)

        //List Review
        reviewList = ArrayList()
        reviewList.add(
            Review(
                "Joan Perkins",
                "3.5",
                "1 days ago",
                "The booking process is fast, and the goods according to the order and the size are also very fitting"
            )
        )
        reviewList.add(
            Review(
                "Frank Garret",
                "3.5",
                "4 days ago",
                "The dress is very nice, and the quality is very good"
            )
        )
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))

        //Setup Recycler View
        reviewsAdapter = ReviewAdapter(this, reviewList)
        rvReview?.setLayoutManager(LinearLayoutManager(this))
        rvReview?.setAdapter(reviewsAdapter)

        //SET LISTENER
        back?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }
}
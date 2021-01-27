package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class WriteReviewActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnSubmitReview: Button? = null
    private var ratingBar: RatingBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnSubmitReview = findViewById(R.id.btn_submit_review)
        ratingBar = findViewById(R.id.rate)
        ratingBar?.setOnRatingBarChangeListener(OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.d(
                "uuu",
                ratingBar.rating.toString() + ""
            )
        })

        //SET LISTENER
        back?.setOnClickListener(this)
        btnSubmitReview?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_submit_review -> {
            }
        }
    }
}
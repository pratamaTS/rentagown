package com.example.rentagown.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ReviewAdapter
import com.example.rentagown.Model.Review
import com.example.rentagown.R

class ReviewFragment : Fragment() {
    var rvReview: RecyclerView? = null
    var reviewList: ArrayList<Review> = ArrayList()
    var reviewAdapter: ReviewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_review, container, false)

        //INIT VIEW
        rvReview = view.findViewById(R.id.rv_review_product)

        //List Review
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
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))
        reviewList.add(Review("Randy Palmet", "3.5", "5 days ago", "the material is pretty good"))

        //Setup Recycler View
        reviewAdapter = ReviewAdapter(context!!, reviewList)
        rvReview!!.setLayoutManager(LinearLayoutManager(activity))
        rvReview!!.setAdapter(reviewAdapter)

        return view
    }
}
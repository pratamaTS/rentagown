package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.DateBookingActivity
import com.example.rentagown.Adapter.SimilarCategoryAdapter
import com.example.rentagown.Model.Product
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class OverviewFragment : Fragment(), View.OnClickListener {
    var rvSimilarCategory: RecyclerView? = null
    var productList: ArrayList<Product> = ArrayList()
    var similarCategoryAdapter: SimilarCategoryAdapter? = null
    var btnDateBooking: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_overview, container, false)

        //INIT VIEW
        rvSimilarCategory = view.findViewById(R.id.rv_similar_category)
        btnDateBooking = view.findViewById(R.id.layout_detail_date_booking_product)


        //List Similar Category
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )
        productList.add(
            Product(
                0,
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Rp. 5.000.000"
            )
        )

        //Setup Recycler View
        similarCategoryAdapter = SimilarCategoryAdapter(context!!, productList)
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvSimilarCategory!!.setLayoutManager(gridLayoutManager)
        rvSimilarCategory!!.setAdapter(similarCategoryAdapter)

        //SET LISTENER
        btnDateBooking!!.setOnClickListener(this@OverviewFragment)
        return view
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_detail_date_booking_product -> {
                val dateBooking = Intent(activity, DateBookingActivity::class.java)
                startActivity(dateBooking)
            }
        }
    }
}
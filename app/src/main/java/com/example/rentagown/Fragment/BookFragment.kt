package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.HistoryBookingActivity
import com.example.rentagown.Adapter.MyBookingAdapter
import com.example.rentagown.Model.MyBooking
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class BookFragment : Fragment(), View.OnClickListener {
    var rvMyBooking: RecyclerView? = null
    var layoutEmpty: LinearLayout? = null
    var myBookingList: ArrayList<MyBooking>? = null
    var adapter: MyBookingAdapter? = null
    var btnHistory: Button? = null
    var btnBrowse: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_book, container, false)

        //INIT VIEW
        rvMyBooking = view.findViewById(R.id.rv_my_booking)
        layoutEmpty = view.findViewById(R.id.layout_booking_empty)
        btnHistory = view.findViewById(R.id.btn_history)
        btnBrowse = view.findViewById(R.id.btn_browse)


        //List My Booking
        myBookingList = ArrayList()
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )
        myBookingList!!.add(
            MyBooking(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "On-Progress",
                "08 Sep 2020",
                "09 Sep 2020 -",
                "16 Sep 2020",
                "Rp. 5.000.000",
                "Rp. 2.000.000",
                "Rp. 3.000.000"
            )
        )


        //Setup Recycler View
        adapter = MyBookingAdapter(context!!, myBookingList!!)
        rvMyBooking!!.setLayoutManager(LinearLayoutManager(activity))
        rvMyBooking!!.setAdapter(adapter)
        if (adapter!!.itemCount > 0) {
            layoutEmpty!!.setVisibility(View.GONE)
        }

        //SET LISTENER
        btnHistory!!.setOnClickListener(this@BookFragment)
        btnBrowse!!.setOnClickListener(this@BookFragment)
        return view
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_history -> {
                val history = Intent(activity, HistoryBookingActivity::class.java)
                startActivity(history)
            }
        }
    }
}
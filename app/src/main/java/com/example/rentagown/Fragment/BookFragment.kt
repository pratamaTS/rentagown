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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.HistoryBookingActivity
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.MainAfterActivity
import com.example.rentagown.Adapter.MyBookingAdapter
import com.example.rentagown.Connection.Interface.MyBookingInterface
import com.example.rentagown.Connection.Presenter.MyBookingPresenter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Model.MyBooking
import com.example.rentagown.R
import com.example.rentagown.Response.MyBooking.DataMyBooking
import java.util.*
import kotlin.collections.ArrayList

class BookFragment : Fragment(), MyBookingInterface, View.OnClickListener {
    var rvMyBooking: RecyclerView? = null
    var layoutEmpty: LinearLayout? = null
    var layoutMyBooking: LinearLayout? = null
    var myBookingList: DataMyBooking? = null
    var adapter: MyBookingAdapter? = null
    var btnHistory: Button? = null
    var btnBrowse: Button? = null
    var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_book, container, false)

        val sessionManager = SessionManager(context!!)

        //INIT VIEW
        rvMyBooking = view.findViewById(R.id.rv_my_booking)
        layoutEmpty = view.findViewById(R.id.layout_booking_empty)
        btnHistory = view.findViewById(R.id.btn_history)
        btnBrowse = view.findViewById(R.id.btn_browse)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        if(token != null) {
            getMyBooking()
        }

        //List My Booking
//        myBookingList = ArrayList()
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )
//        myBookingList!!.add(
//            MyBooking(
//                R.drawable.prewedding_1,
//                "Selina Colourblock Camisole Dress",
//                "On-Progress",
//                "08 Sep 2020",
//                "09 Sep 2020 -",
//                "16 Sep 2020",
//                "Rp. 5.000.000",
//                "Rp. 2.000.000",
//                "Rp. 3.000.000"
//            )
//        )




        //SET LISTENER
        btnHistory!!.setOnClickListener(this@BookFragment)
        btnBrowse!!.setOnClickListener(this@BookFragment)
        return view
    }

    private fun getMyBooking() {
        MyBookingPresenter(this).getMyBooking(context!!)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_history -> {
                val history = Intent(activity, HistoryBookingActivity::class.java)
                startActivity(history)
            }
            R.id.btn_browse -> {
                val mainActivity = Intent(activity, MainActivity::class.java)
                val mainAfterActivity = Intent(activity, MainAfterActivity::class.java)

                if(token!= null){
                    startActivity(mainAfterActivity)
                }else{
                    startActivity(mainActivity)
                }
            }
        }
    }

    override fun onSuccessGetMyBooking(dataMyBooking: DataMyBooking?) {
        myBookingList = dataMyBooking

        if (myBookingList != null) {

            layoutEmpty!!.setVisibility(View.GONE)

            //Setup Recycler View
            adapter = myBookingList?.let { MyBookingAdapter(context!!, it) }
            rvMyBooking!!.setLayoutManager(LinearLayoutManager(activity))
            rvMyBooking!!.setAdapter(adapter)
        } else {
            layoutEmpty!!.setVisibility(View.VISIBLE)
        }
    }

    override fun onErrorGetMyBooking(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    }
}
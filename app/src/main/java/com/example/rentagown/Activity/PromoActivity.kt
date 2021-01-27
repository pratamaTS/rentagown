package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.PromoAdapter
import com.example.rentagown.Model.Promo
import com.example.rentagown.R
import kotlin.collections.ArrayList

class PromoActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvListPromo: RecyclerView? = null
    var promoList: ArrayList<Promo> = ArrayList()
    var promoAdapter: PromoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvListPromo = findViewById(R.id.rv_list_promo)

        //List Promo
        promoList = ArrayList()
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )

        //Setup Recycler View Product
        promoAdapter = PromoAdapter(this, promoList)
        rvListPromo?.setLayoutManager(GridLayoutManager(this, 2))
        rvListPromo?.setAdapter(promoAdapter)

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
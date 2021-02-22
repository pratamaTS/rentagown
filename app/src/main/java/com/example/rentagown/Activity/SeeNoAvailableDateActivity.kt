package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NoAvailableDateAdapter
import com.example.rentagown.Connection.Interface.GetSeeUnDateInterface
import com.example.rentagown.Connection.Presenter.GetSeeUnDatePresenter
import com.example.rentagown.Model.NoAvailableDate
import com.example.rentagown.R
import com.example.rentagown.Response.SeeUnDate.DataSeeUnDate
import java.util.*
import kotlin.collections.ArrayList

class SeeNoAvailableDateActivity : AppCompatActivity(), GetSeeUnDateInterface, View.OnClickListener {
    var back: ImageButton? = null
    var rvNoAvailableDate: RecyclerView? = null
    var availableDateAdapter: NoAvailableDateAdapter? = null
    var idProduct: String? = null
    var productCategory: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var productFinalPrice: Int? = null
    var productPromoAmount: Int? = null
    var productQuantity: Int? = null
    var noAvailableDateList: ArrayList<DataSeeUnDate> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_no_available_date)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvNoAvailableDate = findViewById(R.id.rv_no_available_date)

        idProduct = intent.getStringExtra("id_product")
        productCategory = intent.getStringExtra("name_product_category")
        productName = intent.getStringExtra("product_name")
        productPrice = intent.getIntExtra("product_price", 0)
        productFinalPrice = intent.getIntExtra("final_price", 0)
        productPromoAmount = intent.getIntExtra("promo_amount", 0)
        productQuantity = intent.getIntExtra("product_quantity", 0)

        getSeeUnDate()

        //SET LISTENER
        back?.setOnClickListener(this)
    }

    private fun getSeeUnDate() {
        GetSeeUnDatePresenter(this).getSeeUnDate(this, idProduct.toString())
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }

    override fun onSuccessGetSeeUnDate(dataSeeUnDate: ArrayList<DataSeeUnDate>?) {
        noAvailableDateList = dataSeeUnDate as ArrayList<DataSeeUnDate>

        //Set up Adapter
        availableDateAdapter = NoAvailableDateAdapter(this, noAvailableDateList)
        rvNoAvailableDate?.setLayoutManager(LinearLayoutManager(this))
        rvNoAvailableDate?.setAdapter(availableDateAdapter)
    }

    override fun onErrorGetSeeUnDate(msg: String) {
        Toast.makeText(this, "Failed to get data see unavailable date", Toast.LENGTH_SHORT)
                .show()
    }
}
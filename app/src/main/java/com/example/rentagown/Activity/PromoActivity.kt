package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.PromoAdapter
import com.example.rentagown.Connection.Interface.PromoInterface
import com.example.rentagown.Connection.Presenter.PromoPresenter
import com.example.rentagown.Model.Promo
import com.example.rentagown.R
import com.example.rentagown.Response.Promo.DataPromo
import kotlin.collections.ArrayList

class PromoActivity : AppCompatActivity(), View.OnClickListener, PromoInterface {
    private lateinit var back: ImageButton
    private lateinit var rvListPromo: RecyclerView
    var promoList: ArrayList<DataPromo> = ArrayList()
    var promoAdapter: PromoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvListPromo = findViewById(R.id.rv_list_promo)

        getPromo()

        //SET LISTENER
        back.setOnClickListener(this)
    }

    private fun getPromo() {
        PromoPresenter(this).getAllPromo()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }

    override fun onSuccessGetPromo(dataPromo: ArrayList<DataPromo>?) {
        promoList = dataPromo as ArrayList<DataPromo>

        //Setup Recycler View Product
        promoAdapter = PromoAdapter(this, promoList)
        rvListPromo.setLayoutManager(GridLayoutManager(this, 2))
        rvListPromo.setAdapter(promoAdapter)
    }

    override fun onErrorGetPromo(msg: String) {
        Toast.makeText(this, "Failed to get data promo", Toast.LENGTH_SHORT)
    }
}
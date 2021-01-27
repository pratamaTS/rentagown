package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class DetailPromoActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnUsePromo: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_promo)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnUsePromo = findViewById(R.id.btn_use_promo)

        //SET LISTENER
        back?.setOnClickListener(this)
        btnUsePromo?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_use_promo -> {
            }
        }
    }
}
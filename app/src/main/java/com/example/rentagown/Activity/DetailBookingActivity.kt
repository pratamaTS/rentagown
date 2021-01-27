package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class DetailBookingActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnPay: Button? = null
    var btnFittingSize: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_booking)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnPay = findViewById(R.id.btn_pay_detail_booking)
        btnFittingSize = findViewById(R.id.btn_fitting_size_detail_booking)

        //SET LISTENER
        back?.setOnClickListener(this)
        btnPay?.setOnClickListener(this)
        btnFittingSize?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_pay_detail_booking -> {
                val pay = Intent(this@DetailBookingActivity, FormConfirmationActivity::class.java)
                startActivity(pay)
            }
            R.id.btn_fitting_size_detail_booking -> {
                val fittingSize =
                    Intent(this@DetailBookingActivity, FittingSizeActivity::class.java)
                startActivity(fittingSize)
            }
        }
    }
}
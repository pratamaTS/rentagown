package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class BookingSuccessActivity : AppCompatActivity(), View.OnClickListener {
    var btnInvoice: Button? = null
    var btnBacktoHome: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_success)

        //INIT VIEW
        btnInvoice = findViewById(R.id.btn_invoice_success)
        btnBacktoHome = findViewById(R.id.btn_back_to_home)

        //SET LISTENER
        btnInvoice!!.setOnClickListener(this)
        btnBacktoHome!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_invoice_success -> {
                val invoice = Intent(this@BookingSuccessActivity, InvoiceActivity::class.java)
                startActivity(invoice)
            }
        }
    }
}
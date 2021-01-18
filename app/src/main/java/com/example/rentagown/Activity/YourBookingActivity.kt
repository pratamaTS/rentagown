package com.example.rentagown.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class YourBookingActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnWhatsapp: ImageButton? = null
    var btnCheckout: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_booking)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnCheckout = findViewById(R.id.btn_checkout)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnWhatsapp!!.setOnClickListener(this)
        btnCheckout!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_checkout -> {
                val checkout = Intent(this@YourBookingActivity, PaymentActivity::class.java)
                startActivity(checkout)
            }
            R.id.btn_whatsapp -> {
                val number = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$number"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }
}
package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class ConfirmPaymentActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnConfirmation: Button? = null
    var btnCancel: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnConfirmation = findViewById(R.id.btn_confirmation)
        btnCancel = findViewById(R.id.btn_cancel)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnConfirmation!!.setOnClickListener(this)
        btnCancel!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_confirmation -> {
                val confirmation = Intent(
                    this@ConfirmPaymentActivity,
                    FormConfirmationActivity::class.java
                )
                startActivity(confirmation)
            }
        }
    }
}
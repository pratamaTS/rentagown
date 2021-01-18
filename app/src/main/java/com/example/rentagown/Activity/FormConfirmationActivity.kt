package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class FormConfirmationActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnConfirm: Button? = null
    var btnContinueBooking: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_confirmation)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnConfirm = findViewById(R.id.btn_confirm)
        btnContinueBooking = findViewById(R.id.btn_continue_booking)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnConfirm!!.setOnClickListener(this)
        btnContinueBooking!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_confirm -> {
                val confirm = Intent(
                    this@FormConfirmationActivity,
                    BookingSuccessActivity::class.java
                )
                startActivity(confirm)
            }
        }
    }
}
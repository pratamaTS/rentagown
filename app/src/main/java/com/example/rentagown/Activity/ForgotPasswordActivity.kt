package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    var etEmail: EditText? = null
    var back: ImageButton? = null
    var btnSendEmail: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //INIT VIEW
        etEmail = findViewById(R.id.et_email_forgot_password)
        back = findViewById(R.id.im_back)
        btnSendEmail = findViewById(R.id.btn_send_email)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnSendEmail!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_send_email -> {
                val sendEmail =
                    Intent(this@ForgotPasswordActivity, EmailSuccessActivity::class.java)
                startActivity(sendEmail)
            }
        }
    }
}
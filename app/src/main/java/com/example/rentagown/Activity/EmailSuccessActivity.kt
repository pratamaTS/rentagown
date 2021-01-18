package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class EmailSuccessActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btntoSign: Button? = null
    var btnResendLink: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_success)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btntoSign = findViewById(R.id.btn_to_back_signin)
        btnResendLink = findViewById(R.id.btn_resend_link)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btntoSign!!.setOnClickListener(this)
        btnResendLink!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_to_back_signin -> {
                val backtoLogin = Intent(this@EmailSuccessActivity, SignInActivity::class.java)
                startActivity(backtoLogin)
            }
        }
    }
}
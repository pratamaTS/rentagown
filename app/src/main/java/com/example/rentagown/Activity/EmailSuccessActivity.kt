package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.Connection.Interface.ForgotPasswordInterface
import com.example.rentagown.Connection.Presenter.ForgotPasswordPresenter
import com.example.rentagown.Model.ForgotPassword
import com.example.rentagown.R
import com.example.rentagown.Response.ForgotPassword.ResponseForgotPassword


class EmailSuccessActivity : AppCompatActivity(), ForgotPasswordInterface, View.OnClickListener {
    var back: ImageButton? = null
    var btntoSign: Button? = null
    var btnResendLink: Button? = null
    var email: String? = null
    private lateinit var tvCoundownTimerResend: TextView
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_success)

        //INIT VIEW
        tvCoundownTimerResend = findViewById(R.id.tv_countdown_timer_resend)
        back = findViewById(R.id.im_back)
        btntoSign = findViewById(R.id.btn_to_back_signin)
        btnResendLink = findViewById(R.id.btn_resend_link)

        if(intent.hasExtra("email")){
            email = intent.getStringExtra("email")
        }

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                    .setView(R.layout.layout_loading)
                    .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        countDownTimer()

        //SET LISTENER
        back!!.setOnClickListener(this)
        btntoSign!!.setOnClickListener(this)
        btnResendLink!!.setOnClickListener(this)
    }

    private fun countDownTimer() {
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCoundownTimerResend.visibility = View.VISIBLE
                btnResendLink?.setTextColor(getColor(R.color.colorGray))
                btnResendLink?.isEnabled = false
                tvCoundownTimerResend.setText("" + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                tvCoundownTimerResend.visibility = View.GONE
                btnResendLink?.isEnabled = true
                btnResendLink?.setTextColor(getColor(R.color.colorSecondary))
            }
        }.start()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_resend_link -> {
                loadingDialog.show()
                ForgotPasswordPresenter(this).forgotPassword(this, ForgotPassword(email))
            }
            R.id.btn_to_back_signin -> {
                val mainActivity = Intent(this, MainActivity::class.java)
                mainActivity.putExtra("login_check", true)
                startActivity(mainActivity)
                finish()
            }
        }
    }

    override fun onSuccessForgotPassword(responseForgotPassword: ResponseForgotPassword) {
        loadingDialog.dismiss()
        Toast.makeText(this, "Your password just send to your email", Toast.LENGTH_LONG).show()
        countDownTimer()
    }

    override fun onErrorForgotPassword(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
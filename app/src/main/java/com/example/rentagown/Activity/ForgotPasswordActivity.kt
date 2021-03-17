package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.Connection.Interface.ForgotPasswordInterface
import com.example.rentagown.Connection.Presenter.ForgotPasswordPresenter
import com.example.rentagown.Model.ForgotPassword
import com.example.rentagown.R
import com.example.rentagown.Response.ForgotPassword.ResponseForgotPassword

class ForgotPasswordActivity : AppCompatActivity(), ForgotPasswordInterface, View.OnClickListener {
    var etEmail: EditText? = null
    var back: ImageButton? = null
    var btnSendEmail: Button? = null
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //INIT VIEW
        etEmail = findViewById(R.id.et_email_forgot_password)
        back = findViewById(R.id.im_back)
        btnSendEmail = findViewById(R.id.btn_send_email)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                    .setView(R.layout.layout_loading)
                    .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnSendEmail!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_send_email -> {
                if(etEmail?.text!!.isNotEmpty()) {
                    loadingDialog.show()
                    ForgotPasswordPresenter(this).forgotPassword(this, ForgotPassword(etEmail?.text.toString()))
                }else {
                    Toast.makeText(this, "Email is still empty", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSuccessForgotPassword(responseForgotPassword: ResponseForgotPassword) {
        loadingDialog.dismiss()
        Toast.makeText(this, "Your password just send to your email", Toast.LENGTH_LONG).show()

        val sendEmail =
                Intent(this@ForgotPasswordActivity, EmailSuccessActivity::class.java)
        sendEmail.putExtra("email", etEmail?.text.toString())
        startActivity(sendEmail)
    }

    override fun onErrorForgotPassword(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
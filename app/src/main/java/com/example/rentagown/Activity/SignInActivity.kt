package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var btnSignIn: Button? = null
    var btnForgotPassword: Button? = null
    var btnToSignUp: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //INIT VIEW
        etEmail = findViewById(R.id.et_email_login)
        etPassword = findViewById(R.id.et_password_login)
        btnSignIn = findViewById(R.id.btn_sign_in)
        btnForgotPassword = findViewById(R.id.btn_forgot_password)
        btnToSignUp = findViewById(R.id.btn_to_layout_signup)

        //Set Listener
        btnSignIn!!.setOnClickListener(this)
        btnForgotPassword!!.setOnClickListener(this)
        btnToSignUp!!.setOnClickListener(this)
    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_pass_btn) {
            if (etPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Show Password
                etPassword!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Hide Password
                etPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sign_in -> {
                val login = Intent(this@SignInActivity, MainAfterActivity::class.java)
                startActivity(login)
            }
            R.id.btn_forgot_password -> {
                val forgotPassword = Intent(this@SignInActivity, ForgotPasswordActivity::class.java)
                startActivity(forgotPassword)
            }
            R.id.btn_to_layout_signup -> {
                val toRegister = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(toRegister)
            }
        }
    }
}
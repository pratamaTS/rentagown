package com.example.rentagown.Activity

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

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    var etName: EditText? = null
    var etEmail: EditText? = null
    var etNoHp: EditText? = null
    var etPassword: EditText? = null
    var btnSignUp: Button? = null
    var btnToSignIn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //INIT VIEW
        etName = findViewById(R.id.et_name_register)
        etEmail = findViewById(R.id.et_email_register)
        etNoHp = findViewById(R.id.et_nohp_register)
        etPassword = findViewById(R.id.et_password_register)
        btnSignUp = findViewById(R.id.btn_sign_up)
        btnToSignIn = findViewById(R.id.btn_to_layout_signin)

        //Set Listener
        btnSignUp!!.setOnClickListener(this)
        btnToSignIn!!.setOnClickListener(this)
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sign_up -> {
                val register = Intent(this@SignUpActivity, MainAfterActivity::class.java)
                startActivity(register)
            }
            R.id.btn_to_layout_signin -> {
                val toLogin = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(toLogin)
            }
        }
    }
}
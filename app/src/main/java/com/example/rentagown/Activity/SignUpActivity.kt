package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.Body.RegisterBody
import com.example.rentagown.Connection.Interface.RegisterInterface
import com.example.rentagown.Connection.Presenter.RegisterPresenter
import com.example.rentagown.R

class SignUpActivity : AppCompatActivity(), RegisterInterface, View.OnClickListener {
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
                val email = etEmail!!.text.toString()
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && etName?.text?.isNotEmpty() ?: true && etEmail?.text?.isNotEmpty() ?: true && etPassword?.text?.isNotEmpty() ?: true && etNoHp?.text?.isNotEmpty() ?: true)
                {
                    RegisterPresenter(this).register(
                        RegisterBody(
                            etName!!.text.toString(),
                            etEmail!!.text.toString(),
                            etPassword!!.text.toString(),
                            etNoHp!!.text.toString(),
                            "Android"
                        )
                    )
                }else if(etName?.text.isNullOrEmpty()){
                    Toast.makeText(this, "Name is still empty", Toast.LENGTH_LONG).show()
                }else if(etEmail?.text.isNullOrEmpty()){
                    Toast.makeText(this, "Email is still empty", Toast.LENGTH_LONG).show()
                }else if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() == false){
                    Toast.makeText(this, "$email is INVALID EMAIL !!", Toast.LENGTH_LONG).show()
                }else if(etNoHp?.text.isNullOrEmpty()){
                    Toast.makeText(this, "Phone number is still empty", Toast.LENGTH_LONG).show()
                }else if(etPassword?.text.isNullOrEmpty()){
                    Toast.makeText(this, "Password is still empty", Toast.LENGTH_LONG).show()
                }
            }
            R.id.btn_to_layout_signin -> {
                val toLogin = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(toLogin)
            }
        }
    }

    override fun onSuccessGetRegister() {
//        val toLogin = Intent(this@SignUpActivity, SignInActivity::class.java)
//        toLogin.putExtra("message", "Signup berhasil")
//        startActivity(toLogin)
//        finish()

        val mainActivity = Intent(this, MainActivity::class.java)
        mainActivity.putExtra("login_check", true)
        mainActivity.putExtra("message", "Sign up success")
        startActivity(mainActivity)
        finish()
    }

    override fun onErrorGetRegister(msg: String) {
        Toast.makeText(this, "Sign up Failed, email/phone number has been used", Toast.LENGTH_SHORT).show()
    }
}
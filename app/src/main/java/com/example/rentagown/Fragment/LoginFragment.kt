package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.auth0.android.jwt.JWT
import com.example.rentagown.Activity.ForgotPasswordActivity
import com.example.rentagown.Activity.MainAfterActivity
import com.example.rentagown.Activity.SignUpActivity
import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Connection.Interface.LoginInterface
import com.example.rentagown.Connection.Presenter.LoginPresenter
import com.example.rentagown.R
import com.example.rentagown.Response.Login.DataLogin
import java.util.*


class LoginFragment : Fragment(), View.OnClickListener, LoginInterface {
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var btnSignIn: Button? = null
    var btnForgotPassword: Button? = null
    var btnToSignUp: Button? = null
    var btnShowHidePass: ImageView? = null
    var loginBody: LoginBody? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        //INIT VIEW
        etEmail = view.findViewById(R.id.et_email_login)
        etPassword = view.findViewById(R.id.et_password_login)
        btnSignIn = view.findViewById(R.id.btn_sign_in)
        btnForgotPassword = view.findViewById(R.id.btn_forgot_password)
        btnToSignUp = view.findViewById(R.id.btn_to_layout_signup)
        btnShowHidePass = view.findViewById(R.id.show_pass_btn)

        //Set Listener
        btnSignIn?.setOnClickListener(this@LoginFragment)
        btnForgotPassword?.setOnClickListener(this@LoginFragment)
        btnToSignUp?.setOnClickListener(this@LoginFragment)
        btnShowHidePass?.setOnClickListener(this@LoginFragment)
        return view
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sign_in -> {
                loginBody?.email = etEmail?.text.toString()
                loginBody?.password = etPassword?.text.toString()

                LoginPresenter(this).login(loginBody!!)
            }
            R.id.btn_forgot_password -> {
                val forgotPassword = Intent(activity, ForgotPasswordActivity::class.java)
                startActivity(forgotPassword)
            }
            R.id.btn_to_layout_signup -> {
                val toRegister = Intent(activity, SignUpActivity::class.java)
                startActivity(toRegister)
            }
            R.id.show_pass_btn -> if (v.id == R.id.show_pass_btn) {
                if (etPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                    (v as ImageView).setImageResource(R.drawable.ic_show)
                    //Show Password
                    etPassword!!.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    (v as ImageView).setImageResource(R.drawable.ic_hide)
                    //Hide Password
                    etPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }
    }

    override fun onSuccessGetLogin(dataLogin: DataLogin?) {
        val jwt = JWT(dataLogin?.accessToken.toString())
        val expiresAt: Date? = jwt.expiresAt

        val login = Intent(activity, MainAfterActivity::class.java)
        login.putExtra("token", dataLogin?.accessToken.toString())
        login.putExtra("jwt", jwt)
        login.putExtra("expires_at", expiresAt)
        startActivity(login)
    }

    override fun onErrorGetLogin(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    }
}
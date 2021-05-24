package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
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

    companion object {
        const val TAG = "LoginFragment"

        fun newInstance(listener: Listener? = null): LoginFragment {
            val f = LoginFragment()
            f.listener = listener

            return f
        }
    }

    interface Listener {
        fun onLoggedIn()
    }

    var listener: Listener? = null

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnForgotPassword: Button
    private lateinit var btnToSignUp: Button
    private lateinit var btnShowHidePass: ImageView
    var message: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        //INIT VIEW
        etEmail = view.findViewById(R.id.et_email_login)
        etPassword = view.findViewById(R.id.et_password_login)
        btnSignIn = view.findViewById(R.id.btn_sign_in)
        btnForgotPassword = view.findViewById(R.id.btn_forgot_password)
        btnToSignUp = view.findViewById(R.id.btn_to_layout_signup)
        btnShowHidePass = view.findViewById(R.id.show_pass_btn)

        message = arguments?.getString("message")

        if(message != null){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        //Set Listener
        btnSignIn.setOnClickListener(this@LoginFragment)
        btnForgotPassword.setOnClickListener(this@LoginFragment)
        btnToSignUp.setOnClickListener(this@LoginFragment)
        btnShowHidePass.setOnClickListener(this@LoginFragment)

        return view
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sign_in -> {
                if(isValidEmail(etEmail.text.toString()) == true && etPassword.text?.isNotEmpty() ?:true ) {
                    LoginPresenter(this).login(
                        requireContext(),
                        LoginBody(etEmail.text.toString(), etPassword.text.toString())
                    )
                }else if(etEmail.text.isNullOrEmpty()){
                    Toast.makeText(context, "Email is still empty", Toast.LENGTH_SHORT).show()
                }else if(etPassword.text.isNullOrEmpty()){
                    Toast.makeText(context, "Password is still empty", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show()
                }
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
                if (etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                    (v as ImageView).setImageResource(R.drawable.ic_show)
                    //Show Password
                    etPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    (v as ImageView).setImageResource(R.drawable.ic_hide)
                    //Hide Password
                    etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onSuccessGetLogin() {
        if(listener != null) {
            listener?.onLoggedIn()
        } else {
            val login = Intent(activity, MainAfterActivity::class.java)
            startActivity(login)
            activity?.finish()
            Toast.makeText(context, "Welcome to Rent a Gown", Toast.LENGTH_LONG).show()
        }
    }

    override fun onErrorGetLogin(msg: String) {
        Toast.makeText(context, msg.capitalize().trim(), Toast.LENGTH_SHORT).show()
    }
}
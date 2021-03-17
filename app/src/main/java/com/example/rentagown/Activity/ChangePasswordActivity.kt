package com.example.rentagown.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.Connection.Interface.ChangePasswordInterface
import com.example.rentagown.Connection.Presenter.ChangePasswordPresenter
import com.example.rentagown.Model.ChangePassword
import com.example.rentagown.R
import com.example.rentagown.Response.ChangePassword.DataChangePassword

class ChangePasswordActivity : AppCompatActivity(), ChangePasswordInterface, View.OnClickListener {
    var etNewPassword: EditText? = null
    var etRetypePassword: EditText? = null
    var back: ImageButton? = null
    private lateinit var loadingDialog: AlertDialog
    private lateinit var btnSavePass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        //INIT VIEW
        etNewPassword = findViewById(R.id.et_new_password)
        etRetypePassword = findViewById(R.id.et_retype_password)
        back = findViewById(R.id.im_back)
        btnSavePass = findViewById(R.id.btn_save_password)

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
        btnSavePass.setOnClickListener(this)
    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_pass_btn_1) {
            if (etNewPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Show Password
                etNewPassword!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Hide Password
                etNewPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        if (view.id == R.id.show_pass_btn_2) {
            if (etRetypePassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Show Password
                etRetypePassword!!.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Hide Password
                etRetypePassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_save_password -> {
                if(etNewPassword?.text?.isNotEmpty() ?: true && etRetypePassword?.text?.isNotEmpty() ?: true) {
                    if (etNewPassword?.text.toString() == etRetypePassword?.text.toString()) {
                        loadingDialog.show()
                        ChangePasswordPresenter(this).changePassword(this, ChangePassword(etNewPassword?.text.toString()))
                    } else {
                        Toast.makeText(
                                this,
                                "Password didn't match",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }else if(etNewPassword?.text.isNullOrEmpty()){
                    Toast.makeText(
                            this,
                            "New Password is still empty",
                            Toast.LENGTH_LONG
                    ).show()
                }else{
                    Toast.makeText(
                            this,
                            "Re-Password is still empty",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onSuccessChangePassword(dataChangePassword: DataChangePassword) {
        etNewPassword?.text?.clear()
        etRetypePassword?.text?.clear()
        loadingDialog.dismiss()
        Toast.makeText(this, "Change Password Success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorChangePassword(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(
                this,
                msg,
                Toast.LENGTH_LONG
        ).show()
    }
}
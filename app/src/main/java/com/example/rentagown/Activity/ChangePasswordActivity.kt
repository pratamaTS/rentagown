package com.example.rentagown.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class ChangePasswordActivity : AppCompatActivity(), View.OnClickListener {
    var etNewPassword: EditText? = null
    var etRetypePassword: EditText? = null
    var back: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        //INIT VIEW
        etNewPassword = findViewById(R.id.et_new_password)
        etRetypePassword = findViewById(R.id.et_retype_password)
        back = findViewById(R.id.im_back)

        //SET LISTENER
        back!!.setOnClickListener(this)
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
        }
    }
}
package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.rentagown.Activity.InvoiceHistoryActivity
import com.example.rentagown.Activity.SettingActivity
import com.example.rentagown.Activity.SignInActivity
import com.example.rentagown.Activity.SignUpActivity
import com.example.rentagown.R

class ProfileBeforeFragment : Fragment(), View.OnClickListener {
    var invoiceHistory: ConstraintLayout? = null
    var transactionStatus: ConstraintLayout? = null
    var settings: ConstraintLayout? = null
    var helpCenter: ConstraintLayout? = null
    var privacyPolicy: ConstraintLayout? = null
    var termsConditions: ConstraintLayout? = null
    var signOut: ConstraintLayout? = null
    var btnCreateAccount: Button? = null
    var btnSignIn: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_profile_before, container, false)

        //INIT VIEW
        btnCreateAccount = v.findViewById(R.id.btn_create_account)
        btnSignIn = v.findViewById(R.id.btn_to_layout_login)
        invoiceHistory = v.findViewById(R.id.menu_invoice_history)
        transactionStatus = v.findViewById(R.id.menu_transaction_status)
        settings = v.findViewById(R.id.menu_setting)
        helpCenter = v.findViewById(R.id.menu_help_center)
        privacyPolicy = v.findViewById(R.id.menu_privacy_policy)
        termsConditions = v.findViewById(R.id.menu_terms_condition)
        signOut = v.findViewById(R.id.btn_logout)

        //SET LISTENER
        btnSignIn!!.setOnClickListener(this@ProfileBeforeFragment)
        btnCreateAccount!!.setOnClickListener(this@ProfileBeforeFragment)
        invoiceHistory!!.setOnClickListener(this@ProfileBeforeFragment)
        settings!!.setOnClickListener(this@ProfileBeforeFragment)
        signOut!!.setOnClickListener(this@ProfileBeforeFragment)
        return v
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_to_layout_login -> {
                val login = Intent(activity, SignInActivity::class.java)
                startActivity(login)
            }
            R.id.btn_create_account -> {
                val createAccount = Intent(activity, SignUpActivity::class.java)
                startActivity(createAccount)
            }
            R.id.menu_invoice_history -> {
                Toast.makeText(activity!!.applicationContext, "Invoice History", Toast.LENGTH_SHORT)
                    .show()
                val invoiceHistory = Intent(activity, InvoiceHistoryActivity::class.java)
                startActivity(invoiceHistory)
            }
            R.id.menu_setting -> {
                Toast.makeText(activity!!.applicationContext, "Settings", Toast.LENGTH_SHORT).show()
                val settings = Intent(activity, SettingActivity::class.java)
                startActivity(settings)
            }
        }
    }
}
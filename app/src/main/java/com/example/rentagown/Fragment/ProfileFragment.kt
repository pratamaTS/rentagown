package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.rentagown.Activity.SettingActivity
import com.example.rentagown.Activity.TransactionStatusActivity
import com.example.rentagown.R
import com.example.rentagown.v2.ui.myinvoices.MyInvoicesActivity
import java.util.*

class ProfileFragment : Fragment(), View.OnClickListener {
    var invoiceHistory: ConstraintLayout? = null
    var transactionStatus: ConstraintLayout? = null
    var settings: ConstraintLayout? = null
    var helpCenter: ConstraintLayout? = null
    var privacyPolicy: ConstraintLayout? = null
    var termsConditions: ConstraintLayout? = null
    var signOut: ConstraintLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment\
        val v: View = inflater.inflate(R.layout.fragment_profile, container, false)

        //INIT VIEW
        invoiceHistory = v.findViewById(R.id.menu_invoice_history)
        transactionStatus = v.findViewById(R.id.menu_transaction_status)
        settings = v.findViewById(R.id.menu_setting)
        helpCenter = v.findViewById(R.id.menu_help_center)
        privacyPolicy = v.findViewById(R.id.menu_privacy_policy)
        termsConditions = v.findViewById(R.id.menu_terms_condition)
        signOut = v.findViewById(R.id.btn_logout)

        //SET LISTENER
        invoiceHistory?.setOnClickListener(this@ProfileFragment)
        transactionStatus?.setOnClickListener(this@ProfileFragment)
        settings?.setOnClickListener(this@ProfileFragment)
        helpCenter?.setOnClickListener(this@ProfileFragment)
        termsConditions?.setOnClickListener(this@ProfileFragment)
        signOut?.setOnClickListener(this@ProfileFragment)
        return v
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.menu_invoice_history -> {
                val invoiceHistory = Intent(activity, MyInvoicesActivity::class.java)
                startActivity(invoiceHistory)
            }
            R.id.menu_transaction_status -> {
                Toast.makeText(Objects.requireNonNull(activity)!!, "Transaction Status", Toast.LENGTH_SHORT).show()
                val transactionStatus = Intent(activity, TransactionStatusActivity::class.java)
                startActivity(transactionStatus)
            }
            R.id.menu_setting -> {
                Toast.makeText(Objects.requireNonNull(activity)!!, "Settings", Toast.LENGTH_SHORT).show()
                val settings = Intent(activity, SettingActivity::class.java)
                startActivity(settings)
            }
        }
    }
}
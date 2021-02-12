package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.rentagown.Activity.InvoiceHistoryActivity
import com.example.rentagown.Activity.SettingActivity
import com.example.rentagown.Connection.Constants
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Presenter.ProfilePresenter
import com.example.rentagown.R
import com.example.rentagown.Response.Profile.DataProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileAfterFragment : Fragment(), View.OnClickListener, ProfileInterface {
    var invoiceHistory: ConstraintLayout? = null
    var transactionStatus: ConstraintLayout? = null
    var settings: ConstraintLayout? = null
    var helpCenter: ConstraintLayout? = null
    var privacyPolicy: ConstraintLayout? = null
    var termsConditions: ConstraintLayout? = null
    var signOut: ConstraintLayout? = null
    var tvName: TextView? = null
    var tvEmail: TextView? = null
    var imProfile: CircleImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        val v: View = inflater.inflate(R.layout.fragment_profile_after, container, false)

        //INIT VIEW
        invoiceHistory = v.findViewById(R.id.menu_invoice_history)
        transactionStatus = v.findViewById(R.id.menu_transaction_status)
        settings = v.findViewById(R.id.menu_setting)
        helpCenter = v.findViewById(R.id.menu_help_center)
        privacyPolicy = v.findViewById(R.id.menu_privacy_policy)
        termsConditions = v.findViewById(R.id.menu_terms_condition)
        signOut = v.findViewById(R.id.btn_logout)
        tvName = v.findViewById(R.id.tv_name)
        tvEmail = v.findViewById(R.id.tv_email)
        imProfile = v.findViewById(R.id.foto_profile)

        getProfile()

        //SET LISTENER
        invoiceHistory!!.setOnClickListener(this@ProfileAfterFragment)
        settings!!.setOnClickListener(this@ProfileAfterFragment)
        signOut!!.setOnClickListener(this@ProfileAfterFragment)
        return v
    }

    private fun getProfile() {
        ProfilePresenter(this).getProfile(context!!)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
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

    override fun onSuccessGetPromo(dataProfile: DataProfile?) {
        tvName!!.text = dataProfile?.name?.capitalize()?.trim()
        tvEmail!!.text = dataProfile?.email

        if(dataProfile?.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = Constants.SERVER_URL + dataProfile?.pathPhoto
            Picasso.get().load(imgURL).into(imProfile)
        }else {
            imProfile?.setImageResource(R.drawable.family_1)
        }
    }

    override fun onErrorGetPromo(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    }
}
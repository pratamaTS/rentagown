package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.rentagown.Activity.*
import com.example.rentagown.BuildConfig
import com.example.rentagown.Connection.Constants
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Presenter.ProfilePresenter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.R
import com.example.rentagown.Response.Profile.DataProfile
import com.example.rentagown.v2.ui.myinvoices.MyInvoicesActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileAfterFragment : Fragment(), View.OnClickListener, ProfileInterface {
    private lateinit var sessionManager: SessionManager
    val TAG = ProfileAfterFragment::class.java.simpleName

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
        settings = v.findViewById(R.id.menu_setting)
        helpCenter = v.findViewById(R.id.menu_help_center)
        privacyPolicy = v.findViewById(R.id.menu_privacy_policy)
        termsConditions = v.findViewById(R.id.menu_terms_condition)
        signOut = v.findViewById(R.id.btn_logout)
        tvName = v.findViewById(R.id.tv_name_profile)
        tvEmail = v.findViewById(R.id.tv_email_profile)
        imProfile = v.findViewById(R.id.foto_profile)

        getProfile()

        //SET LISTENER
        invoiceHistory!!.setOnClickListener(this@ProfileAfterFragment)
        helpCenter!!.setOnClickListener(this)
        privacyPolicy!!.setOnClickListener(this)
        termsConditions!!.setOnClickListener(this)
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
                val invoiceHistory = Intent(activity, MyInvoicesActivity::class.java)
                startActivity(invoiceHistory)
            }
            R.id.menu_setting -> {
                val settings = Intent(activity, SettingActivity::class.java)
                startActivity(settings)
            }
            R.id.btn_logout -> {
                sessionManager = SessionManager(context!!)
                sessionManager.logOut()

                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            R.id.menu_help_center -> {
                val phoneNumber = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$phoneNumber"
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                    startActivity(this)
                }
            }
            R.id.menu_privacy_policy -> {
                val privacyPolicyActivity = Intent(activity, PrivacyPolicyActivity::class.java)
                startActivity(privacyPolicyActivity)
            }
            R.id.menu_terms_condition -> {
                val termsConditionActivity = Intent(activity, TermsConditionActivity::class.java)
                startActivity(termsConditionActivity)
            }
        }
    }

    override fun onSuccessGetProfile(dataProfile: DataProfile?) {
        tvName?.text = dataProfile?.name?.capitalize()?.trim()
        tvEmail?.text = dataProfile?.email

        if(dataProfile?.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = BuildConfig.BASE_PHOTO_URL + dataProfile?.pathPhoto
            Picasso.get().load(imgURL).into(imProfile)
        }else {
            imProfile?.setImageResource(R.drawable.bg_placeholder)
        }
    }

    override fun onErrorGetProfile(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    }
}
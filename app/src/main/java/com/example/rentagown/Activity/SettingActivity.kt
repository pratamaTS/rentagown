package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rentagown.R

class SettingActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    var back: ImageButton? = null
    var editProfile: ConstraintLayout? = null
    var changePassword: ConstraintLayout? = null
    var changeAddress: ConstraintLayout? = null
    var pushCatalogUpdate: SwitchCompat? = null
    var pushPromo: SwitchCompat? = null
    var pushTransactions: SwitchCompat? = null
    var sharedPreferences: SharedPreferences? = null
    var PACKAGE_NAME = "com.example.rentagown"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        editProfile = findViewById(R.id.menu_edit_profile)
        changePassword = findViewById(R.id.menu_change_password)
        changeAddress = findViewById(R.id.menu_change_address)
        pushCatalogUpdate = findViewById(R.id.toogle_switch_catalog_update)
        pushPromo = findViewById(R.id.toogle_switch_promo)
        pushTransactions = findViewById(R.id.toogle_switch_transactions)

        //SET LISTENER
        back!!.setOnClickListener(this)
        editProfile!!.setOnClickListener(this)
        changePassword!!.setOnClickListener(this)
        changeAddress!!.setOnClickListener(this)
        if (pushCatalogUpdate != null) {
            pushCatalogUpdate!!.setOnCheckedChangeListener(this)
        }
        if (pushPromo != null) {
            pushPromo!!.setOnCheckedChangeListener(this)
        }
        if (pushTransactions != null) {
            pushTransactions!!.setOnCheckedChangeListener(this)
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.menu_edit_profile -> {
                val editProfile = Intent(this@SettingActivity, EditProfileActivity::class.java)
                startActivity(editProfile)
            }
            R.id.menu_change_password -> {
                val changePassword =
                    Intent(this@SettingActivity, ChangePasswordActivity::class.java)
                startActivity(changePassword)
            }
            R.id.menu_change_address -> {
                val changeAddress = Intent(this@SettingActivity, ChangeAddressActivity::class.java)
                startActivity(changeAddress)
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.toogle_switch_catalog_update -> {
                Toast.makeText(
                    this@SettingActivity,
                    "Notification switch " + if (isChecked) "on" else "off",
                    Toast.LENGTH_SHORT
                ).show()
                if (isChecked) {
                    Toast.makeText(this@SettingActivity, "Notification On", Toast.LENGTH_SHORT)
                        .show()
                    pushCatalogUpdate!!.isChecked = true
                    saveNotifSetting(true)
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushCatalogUpdate!!.isChecked = false
                    saveNotifSetting(false)
                }
            }
            R.id.toogle_switch_promo -> {
                Toast.makeText(
                    this@SettingActivity,
                    "Notification switch " + if (isChecked) "on" else "off",
                    Toast.LENGTH_SHORT
                ).show()
                if (isChecked) {
                    Toast.makeText(this@SettingActivity, "Notification On", Toast.LENGTH_SHORT)
                        .show()
                    pushPromo!!.isChecked = true
                    saveNotifSetting(true)
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushPromo!!.isChecked = false
                    saveNotifSetting(false)
                }
            }
            R.id.toogle_switch_transactions -> {
                Toast.makeText(
                    this@SettingActivity,
                    "Notification switch " + if (isChecked) "on" else "off",
                    Toast.LENGTH_SHORT
                ).show()
                if (isChecked) {
                    Toast.makeText(this@SettingActivity, "Notification On", Toast.LENGTH_SHORT)
                        .show()
                    pushTransactions!!.isChecked = true
                    saveNotifSetting(true)
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushTransactions!!.isChecked = false
                    saveNotifSetting(false)
                }
            }
        }
    }

    private fun saveNotifSetting(notification: Boolean) {
        sharedPreferences =
            applicationContext.getSharedPreferences(PACKAGE_NAME + "SETTING_NOTIF", MODE_PRIVATE)
        sharedPreferences!!.getBoolean("notif", false)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("notif", notification)
        editor.apply()
    }
}
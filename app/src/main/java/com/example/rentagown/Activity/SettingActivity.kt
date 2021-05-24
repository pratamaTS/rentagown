package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rentagown.Connection.Interface.GetDefaultAddressInterface
import com.example.rentagown.Connection.Interface.SetDefaultAddressInterface
import com.example.rentagown.Connection.Presenter.GetDefaultAddressPresenter
import com.example.rentagown.Connection.Presenter.SetDefaultAddressPresenter
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.ReqSetAddress
import com.example.rentagown.v2.ui.bookingsuccess.BookingSuccessActivity
import com.example.rentagown.v2.ui.chooseaddress.ChooseAddressActivity
import com.example.rentagown.v2.ui.choosebank.ChooseBankActivity
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity

class SettingActivity : AppCompatActivity(), View.OnClickListener, SetDefaultAddressInterface,
    CompoundButton.OnCheckedChangeListener {
    private lateinit var back: ImageButton
    private lateinit var editProfile: ConstraintLayout
    private lateinit var changePassword: ConstraintLayout
    private lateinit var changeAddress: ConstraintLayout
    private lateinit var pushCatalogUpdate: SwitchCompat
    private lateinit var pushPromo: SwitchCompat
    private lateinit var pushTransactions: SwitchCompat
    var sharedPreferences: SharedPreferences? = null
    private var selectedAddress: Address? = null
    var PACKAGE_NAME = "com.example.rentagown"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            getSharedPreferences(PACKAGE_NAME + "SETTING_NOTIF", MODE_PRIVATE)
        setContentView(R.layout.activity_setting)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        editProfile = findViewById(R.id.menu_edit_profile)
        changePassword = findViewById(R.id.menu_change_password)
        changeAddress = findViewById(R.id.menu_change_address)
        pushCatalogUpdate = findViewById(R.id.toogle_switch_catalog_update)
        pushPromo = findViewById(R.id.toogle_switch_promo)
        pushTransactions = findViewById(R.id.toogle_switch_transactions)
        pushCatalogUpdate.isChecked = sharedPreferences!!.getBoolean("notif-"+"catalog_update", false)
        pushPromo.isChecked = sharedPreferences!!.getBoolean("notif-"+"promo", true)
        pushTransactions.isChecked = sharedPreferences!!.getBoolean("notif-"+"transactions", true)

        //SET LISTENER
        back.setOnClickListener(this)
        editProfile.setOnClickListener(this)
        changePassword.setOnClickListener(this)
        changeAddress.setOnClickListener(this)
        pushCatalogUpdate.setOnCheckedChangeListener(this)
        pushPromo.setOnCheckedChangeListener(this)
        pushTransactions.setOnCheckedChangeListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ChooseAddressActivity.REQ_CHOOSE_ADDRESS) {
            if(resultCode == RESULT_OK) {
                selectedAddress = data?.getParcelableExtra("selected_address")
                val editedAddress = data?.getParcelableExtra<Address>("edited_address")
                val deletedAddress = data?.getParcelableExtra<Address>("deleted_address")
                val default: Boolean? = data?.getBooleanExtra("default", false)

                when {
                    selectedAddress != null -> {
                        onAddressSelected(selectedAddress, default!!)
                    }
//                    editedAddress != null -> {
//                        presenter.onAddressEdited(editedAddress)
//                    }
//                    deletedAddress != null -> {
//                        presenter.onAddressDeleted(deletedAddress)
//                    }
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun onAddressSelected(address: Address?, default: Boolean) {
        address?.let {
            val mReqSetAddress = ReqSetAddress(
                    addressId = it.addressId
            )

            when(default) {
                true -> {
                    SetDefaultAddressPresenter(this).setDefaultAddress(this, mReqSetAddress)
                }
                false -> if(address.isDefault == 1)Toast.makeText(
                    this,
                    "The address is already set to default",
                    Toast.LENGTH_LONG
                ).show()

            }
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
                Intent(this, ChooseAddressActivity::class.java).apply {
                    putExtra("selected_address_id", selectedAddress?.addressId)

                    startActivityForResult(this, ChooseAddressActivity.REQ_CHOOSE_ADDRESS)
                }
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
                    pushCatalogUpdate.isChecked = true
                    saveNotifSetting(true,"catalog_update")
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushCatalogUpdate.isChecked = false
                    saveNotifSetting(false,"catalog_update")
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
                    pushPromo.isChecked = true
                    saveNotifSetting(true,"promo")
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushPromo.isChecked = false
                    saveNotifSetting(false,"promo")
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
                    pushTransactions.isChecked = true
                    saveNotifSetting(true,"transactions")
                } else {
                    Toast.makeText(this@SettingActivity, "Notification Off", Toast.LENGTH_SHORT)
                        .show()
                    pushTransactions.isChecked = false
                    saveNotifSetting(false,"transactions")
                }
            }
        }
    }

    private fun saveNotifSetting(notification: Boolean,tipe: String) {
//        sharedPreferences!!.getBoolean("notif", false)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("notif-"+tipe, notification)
        editor.apply()
    }

    override fun onSuccessSetDefaultAddress(address: Address) {
        Toast.makeText(this, "Set default address success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorSetDefaultAddress(msg: String) {
        Toast.makeText(this, "Set default address failed", Toast.LENGTH_LONG).show()
    }
}
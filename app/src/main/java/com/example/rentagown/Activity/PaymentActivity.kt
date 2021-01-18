package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class PaymentActivity : AppCompatActivity(), View.OnClickListener {
    var btnChoosePaymentMethod: LinearLayout? = null
    var back: ImageButton? = null
    var btnWhatsaap: ImageButton? = null
    var btnPayment: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnChoosePaymentMethod = findViewById(R.id.btn_choose_payment_method)
        btnPayment = findViewById(R.id.btn_payment)
        btnWhatsaap = findViewById(R.id.btn_whatsapp)

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnChoosePaymentMethod!!.setOnClickListener(this)
        btnPayment!!.setOnClickListener(this)
        btnWhatsaap!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_choose_payment_method -> {
                val bottomSheetDialog =
                    BottomSheetDialog(this@PaymentActivity, R.style.BottomSheetDialogTheme)
                val bottomSheetView: View = LayoutInflater.from(applicationContext).inflate(
                    R.layout.layout_bottom_sheet_payment_method,
                    findViewById(R.id.bottom_sheet_container)
                )

//                RadioGroup radioGroup = findViewById(R.id.radio_group);
//                RadioButton selectPaymentButton;
//
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                selectPaymentButton = (RadioButton) findViewById(selectedId);
//                if (selectedId == 0){
//                    Toast.makeText(PaymentActivity.this,"Nothing Selected", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(PaymentActivity.this, selectPaymentButton.getText(), Toast.LENGTH_SHORT).show();
//                }
                bottomSheetView.findViewById<View>(R.id.btn_close)
                    .setOnClickListener { bottomSheetDialog.dismiss() }
                bottomSheetView.findViewById<View>(R.id.btn_continue).setOnClickListener {
                    Toast.makeText(this@PaymentActivity, "Payment Method", Toast.LENGTH_SHORT)
                        .show()
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
            R.id.btn_payment -> {
                val payment = Intent(this@PaymentActivity, ConfirmPaymentActivity::class.java)
                startActivity(payment)
            }
            R.id.btn_whatsapp -> {
                val number = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$number"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }
}
package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.NumberFormat
import java.util.*


class PaymentActivity : AppCompatActivity(), View.OnClickListener {
    var btnChoosePaymentMethod: LinearLayout? = null
    var back: ImageButton? = null
    var btnWhatsaap: ImageButton? = null
    var btnPayment: Button? = null
    var btnAddAddress: Button? = null
    var btnChangeAddress: Button? = null
    var layoutDP: RelativeLayout? = null
    var tvPaymentMethod: TextView? = null
    var tvFullPaymentPrice: TextView? = null
    var tvDownPaymentPrice: TextView? = null
    var tvDPPrice: TextView? = null
    var tvTotalPayment: TextView? = null
    var tvAddressLabel: TextView? = null
    var tvAddressName: TextView? = null
    var tvAddressNoHP: TextView? = null
    var tvAddressDetail: TextView? = null
    var tvServices: TextView? = null
    var layoutAddAddress: LinearLayout? = null
    var layoutShowAddress: RelativeLayout? = null
    var idProduct: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var phone: String? = null
    var services: Int? = null
    var startDate: String? = null
    var endDate: String? = null
    var idAddress: String? = null
    var addressLabel: String? = null
    var address: String? = null
    var addressDetail: String? = null
    var name: String? = null
    var paymentMethod: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        tvAddressLabel = findViewById(R.id.tv_address_label)
        tvAddressName = findViewById(R.id.tv_address_name)
        tvAddressNoHP = findViewById(R.id.tv_address_nohp)
        tvAddressDetail = findViewById(R.id.tv_address_detail)
        tvPaymentMethod = findViewById(R.id.tv_payment_method_selected)
        tvFullPaymentPrice = findViewById(R.id.tv_price_dress_payment_1)
        tvDownPaymentPrice = findViewById(R.id.tv_price_dress_payment_2)
        tvDPPrice = findViewById(R.id.tv_price_deposit_booking)
        tvTotalPayment = findViewById(R.id.tv_price_total_payment)
        tvServices = findViewById(R.id.tv_services)
        layoutDP = findViewById(R.id.summary_price_deposit_booking)
        btnChoosePaymentMethod = findViewById(R.id.btn_choose_payment_method)
        btnPayment = findViewById(R.id.btn_payment)
        btnWhatsaap = findViewById(R.id.btn_whatsapp)
        btnAddAddress = findViewById(R.id.btn_add_address)
        btnChangeAddress = findViewById(R.id.btn_change_address)
        layoutAddAddress = findViewById(R.id.layout_button_add_address)
        layoutShowAddress = findViewById(R.id.layout_choose_address)
        idProduct = intent.getStringExtra("id_product")
        productName = intent.getStringExtra("product_name")
        phone = intent.getStringExtra("phone")
        productPrice = intent.getIntExtra("paid_price", 0)
        services = intent.getIntExtra("services", 0)
        startDate = intent.getStringExtra("start_date")
        endDate = intent.getStringExtra("end_date")

        when(services){
            0 -> tvServices!!.text = "Regular Services"
            1 -> tvServices!!.text = "One Day Services"
        }

        if(intent.hasExtra("id_address")){
            idAddress = intent.getStringExtra("id_address")
            name = intent.getStringExtra("name")
            addressLabel = intent.getStringExtra("address_label")
            address = intent.getStringExtra("address")
            addressDetail = intent.getStringExtra("address_detail")

            layoutAddAddress?.visibility = View.GONE
            layoutShowAddress?.visibility = View.VISIBLE

            tvAddressLabel?.text = addressLabel
            tvAddressName?.text = name
            tvAddressNoHP?.text = phone
            tvAddressDetail?.text = address
        }

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnChoosePaymentMethod!!.setOnClickListener(this)
        btnPayment!!.setOnClickListener(this)
        btnWhatsaap!!.setOnClickListener(this)
        btnAddAddress!!.setOnClickListener(this)
        btnChangeAddress!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_add_address -> {
                val addAddress = Intent(this@PaymentActivity, AddAddressActivity::class.java)
                addAddress.putExtra("paid_price", productPrice)
                addAddress.putExtra("services", services)
                addAddress.putExtra("product_name", productName)
                addAddress.putExtra("id_product", idProduct)
                addAddress.putExtra("phone", phone)
                addAddress.putExtra("start_date", startDate)
                addAddress.putExtra("end_date", endDate)
                startActivity(addAddress)
                finish()
            }
            R.id.btn_change_address -> {
                val changeAddress = Intent(this@PaymentActivity, ChangeAddressActivity::class.java)
                changeAddress.putExtra("paid_price", productPrice)
                changeAddress.putExtra("services", services)
                changeAddress.putExtra("product_name", productName)
                changeAddress.putExtra("id_product", idProduct)
                changeAddress.putExtra("phone", phone)
                changeAddress.putExtra("start_date", startDate)
                changeAddress.putExtra("end_date", endDate)
                startActivity(changeAddress)
                finish()
            }
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

                val radioGroupB = bottomSheetView.findViewById<RadioGroup>(R.id.radio_group)
                bottomSheetView.findViewById<View>(R.id.btn_close)
                        .setOnClickListener { bottomSheetDialog.dismiss() }
                bottomSheetView.findViewById<View>(R.id.btn_continue).setOnClickListener {
                    // get selected radio button from radioGroup
                    val localeID =  Locale("in", "ID")
                    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
                    val selectedId: Int = radioGroupB.checkedRadioButtonId

                    Log.d("selected", selectedId.toString())

                    // find the radiobutton by returned id
                    val radioButton = bottomSheetView.findViewById<View>(selectedId) as RadioButton

                    tvPaymentMethod!!.text = radioButton.text

                    when(selectedId){
                        2131296771 -> {
                            tvDPPrice!!.text = numberFormat.format(500000)
                            val minusPrice = productPrice?.minus(500000)
                            tvDownPaymentPrice!!.text = "-" + numberFormat.format(minusPrice)
                            tvTotalPayment!!.text = numberFormat.format(500000)
                        }
                        else -> {
                            layoutDP!!.visibility = View.GONE
                            tvDownPaymentPrice!!.visibility = View.GONE
                            tvFullPaymentPrice!!.visibility = View.VISIBLE
                            tvFullPaymentPrice!!.text = numberFormat.format(productPrice)
                            tvTotalPayment!!.text = numberFormat.format(productPrice)
                        }
                    }
                }

                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
            R.id.btn_payment -> {
                val payment = Intent(this@PaymentActivity, ChangeBankActivity::class.java)
                payment.putExtra("paid_price", productPrice)
                payment.putExtra("services", services)
                payment.putExtra("product_name", productName)
                payment.putExtra("id_product", idProduct)
                payment.putExtra("phone", phone)
                payment.putExtra("start_date", startDate)
                payment.putExtra("end_date", endDate)
                startActivity(payment)
                finish()
            }
            R.id.btn_whatsapp -> {
                val number = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$number"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                finish()
            }
        }
    }
}
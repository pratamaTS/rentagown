package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.Body.AddAddressBody
import com.example.rentagown.Connection.Interface.AddAddressInterface
import com.example.rentagown.Connection.Presenter.AddAddressPresenter
import com.example.rentagown.R
import com.example.rentagown.Response.CreateAddress.DataAddAddress

class AddAddressActivity : AppCompatActivity(), AddAddressInterface, View.OnClickListener {
    var back: ImageButton? = null
    var btnSaveAddress: Button? = null
    var edtAddressLabel: EditText? = null
    var edtAddress: EditText? = null
    var edtNameAddress: EditText? = null
    var edtNoHp: EditText? = null
    var idProduct: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var phone: String? = null
    var services: Int? = null
    var startDate: String? = null
    var endDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        edtAddressLabel = findViewById(R.id.et_address_label)
        edtAddress = findViewById(R.id.et_address)
        edtNameAddress = findViewById(R.id.et_name_address)
        edtNoHp = findViewById(R.id.et_nohp_address)
        btnSaveAddress = findViewById(R.id.btn_save_address)

        idProduct = intent.getStringExtra("id_product")
        productName = intent.getStringExtra("product_name")
        phone = intent.getStringExtra("phone")
        productPrice = intent.getIntExtra("paid_price", 0)
        services = intent.getIntExtra("services", 0)
        startDate = intent.getStringExtra("start_date")
        endDate = intent.getStringExtra("end_date")

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnSaveAddress!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_save_address -> {
                AddAddressPresenter(this).addAddress(this, AddAddressBody(
                        edtNameAddress?.text.toString(),
                        edtAddress?.text.toString(),
                        edtNoHp?.text.toString(),
                        edtNameAddress?.text.toString(),
                        edtAddressLabel?.text.toString()
                ))
            }
        }
    }

    override fun onSuccessAddAddress(dataAddAddress: DataAddAddress) {

        val checkout = Intent(this, PaymentActivity::class.java)
        checkout.putExtra("paid_price", productPrice)
        checkout.putExtra("services", services)
        checkout.putExtra("product_name", productName)
        checkout.putExtra("id_product", idProduct)
        checkout.putExtra("start_date", startDate)
        checkout.putExtra("end_date", endDate)
        checkout.putExtra("id_address", dataAddAddress.idAddress)
        checkout.putExtra("name", dataAddAddress.name)
        checkout.putExtra("phone", dataAddAddress.phone)
        checkout.putExtra("address_label", dataAddAddress.addressLabel)
        checkout.putExtra("address", dataAddAddress.address)
        checkout.putExtra("address_detail", dataAddAddress.addressDetail)
        startActivity(checkout)
        finish()
    }

    override fun onErrorAddAddress(msg: String) {
        Toast.makeText(this, "Failed to add address", Toast.LENGTH_SHORT)
    }
}
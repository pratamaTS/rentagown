package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ChangeAddressAdapter
import com.example.rentagown.Connection.Interface.GetAddressInterface
import com.example.rentagown.Connection.Presenter.GetAddressPresenter
import com.example.rentagown.Model.ChangeAddress
import com.example.rentagown.R
import com.example.rentagown.Response.GetAddress.DataAddress
import java.util.*
import kotlin.collections.ArrayList

class ChangeAddressActivity : AppCompatActivity(), GetAddressInterface, View.OnClickListener {
    var rvListAddress: RecyclerView? = null
    var back: ImageButton? = null
    var btnSave: Button? = null
    var addAddress: ImageButton? = null
    var changeAddressList: ArrayList<DataAddress> = ArrayList()
    var adapter: ChangeAddressAdapter? = null
    var idProduct: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var phone: String? = null
    var services: Int? = null
    var startDate: String? = null
    var endDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_address)

        //INIT VIEW
        rvListAddress = findViewById(R.id.rv_list_address)
        back = findViewById(R.id.im_back)
        addAddress = findViewById(R.id.im_add_address)

        idProduct = intent.getStringExtra("id_product")
        productName = intent.getStringExtra("product_name")
        phone = intent.getStringExtra("phone")
        productPrice = intent.getIntExtra("paid_price", 0)
        services = intent.getIntExtra("services", 0)
        startDate = intent.getStringExtra("start_date")
        endDate = intent.getStringExtra("end_date")

        //List Address
        getAddress()

        //SET LISTENER
        back!!.setOnClickListener(this)
        addAddress!!.setOnClickListener(this)
    }

    private fun getAddress() {
        GetAddressPresenter(this).getAllAddress(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.im_add_address -> {
                val addAddress = Intent(this@ChangeAddressActivity, AddAddressActivity::class.java)
                addAddress.putExtra("paid_price", productPrice)
                addAddress.putExtra("services", services)
                addAddress.putExtra("product_name", productName)
                addAddress.putExtra("id_product", idProduct)
                addAddress.putExtra("phone", phone)
                addAddress.putExtra("start_date", startDate)
                addAddress.putExtra("end_date", endDate)
                startActivity(addAddress)
            }
        }
    }

    override fun onSuccessGetAddress(dataAddress: ArrayList<DataAddress>?) {
        changeAddressList = dataAddress as ArrayList<DataAddress>

        //Setup Adapter
        adapter = ChangeAddressAdapter(changeAddressList, this)
        rvListAddress!!.setLayoutManager(LinearLayoutManager(this))
        rvListAddress!!.setAdapter(adapter)
    }

    override fun onErrorGetAddress(msg: String) {
        Toast.makeText(this, "Failed to get data address", Toast.LENGTH_SHORT).show()
    }
}
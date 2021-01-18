package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ItemInvoiceAdapter
import com.example.rentagown.Model.Invoice
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class InvoiceActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvItemInvoice: RecyclerView? = null
    var adapter: ItemInvoiceAdapter? = null
    var invoiceList: ArrayList<Invoice> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvItemInvoice = findViewById(R.id.rv_item_invoice)

        //List Invoice
        invoiceList.add(Invoice("Selina Colourblock Camisole Dress", "Rp. 5.000.000"))
        invoiceList.add(Invoice("Selina Colourblock Camisole Dress", "Rp. 5.000.000"))
        invoiceList.add(Invoice("Selina Colourblock Camisole Dress", "Rp. 5.000.000"))

        //Setup adapter
        adapter = ItemInvoiceAdapter(this, invoiceList)
        rvItemInvoice!!.setLayoutManager(LinearLayoutManager(this))
        rvItemInvoice!!.setAdapter(adapter)

        //SET LISTENER
        back!!.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }
}
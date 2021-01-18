package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.InvoiceHistoryAdapter
import com.example.rentagown.Model.InvoiceHistory
import com.example.rentagown.R
import java.util.*

class InvoiceHistoryActivity : AppCompatActivity(), View.OnClickListener {
    var rvInvoiceHistory: RecyclerView? = null
    var back: ImageButton? = null
    var invoiceHistoryList: ArrayList<InvoiceHistory>? = null
    var adapter: InvoiceHistoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_history)

        //INIT VIEW
        rvInvoiceHistory = findViewById(R.id.rv_invoice_history)
        back = findViewById(R.id.im_back)

        //List Invoice History
        invoiceHistoryList = ArrayList()
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )
        invoiceHistoryList!!.add(
            InvoiceHistory(
                "INV-08092020-070001",
                "Transfer Manual",
                "10:15",
                "08 Sep 2020",
                "Rp. 2.000.000"
            )
        )

        //Set up Adapter
        adapter = InvoiceHistoryAdapter(invoiceHistoryList!!, this)
        rvInvoiceHistory!!.setLayoutManager(LinearLayoutManager(this))
        rvInvoiceHistory!!.setAdapter(adapter)

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
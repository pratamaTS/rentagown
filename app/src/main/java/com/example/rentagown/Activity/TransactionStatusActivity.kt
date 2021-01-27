package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.TransactionStatusAdapter
import com.example.rentagown.Model.TransactionStatus
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class TransactionStatusActivity : AppCompatActivity(), View.OnClickListener {
    var rvTransactionStatus: RecyclerView? = null
    var layoutEmpty: LinearLayout? = null
    var btnRepayment: Button? = null
    var back: ImageButton? = null
    var transactionStatusList: ArrayList<TransactionStatus> = ArrayList()
    var transactionStatusAdapter: TransactionStatusAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_status)

        //INIT VIEW
        rvTransactionStatus = findViewById(R.id.rv_transaction_status)
        back = findViewById(R.id.im_back)
        btnRepayment = findViewById(R.id.btn_repayment)
        layoutEmpty = findViewById(R.id.layout_transaction_empty)

        //List Invoice History
        transactionStatusList = ArrayList()
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))
        transactionStatusList?.add(TransactionStatus(
                "08 SEP 2020",
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "10:15",
                "Deposit Booking",
                "Rp. 500.000"))

        //Set up Adapter
        transactionStatusAdapter = TransactionStatusAdapter(this, transactionStatusList)
        rvTransactionStatus?.setLayoutManager(LinearLayoutManager(this))
        rvTransactionStatus?.setAdapter(transactionStatusAdapter)
        if (transactionStatusAdapter!!.itemCount > 0) {
            layoutEmpty?.setVisibility(View.GONE)
        }

        //SET LISTENER
        back?.setOnClickListener(this)
        btnRepayment?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_repayment -> {
            }
        }
    }
}
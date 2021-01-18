package com.example.rentagown.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NotifTransactionAdapter
import com.example.rentagown.Model.NotifTransaction
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class NotifTransactionFragment : Fragment() {
    var rvNotifTransaction: RecyclerView? = null
    var notifTransactionList: ArrayList<NotifTransaction>? = null
    var notifTransactionAdapter: NotifTransactionAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notif_transaction, container, false)

        //INIT VIEW
        rvNotifTransaction = view.findViewById(R.id.rv_notif_transaction)

        //List
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )
        notifTransactionList!!.add(
            NotifTransaction(
                "08 SEP 2020",
                "Prewedding David & Josephine",
                "Sep 9 - Sep 12",
                "07:00-12:00 am",
                "Deposit Booking",
                "Rp. 2.000.000",
                "Successfull"
            )
        )

        //Setup Recycler View
        notifTransactionAdapter = NotifTransactionAdapter(context!!, notifTransactionList!!)
        rvNotifTransaction!!.setLayoutManager(LinearLayoutManager(activity))
        rvNotifTransaction!!.setAdapter(notifTransactionAdapter)
        return view
    }
}
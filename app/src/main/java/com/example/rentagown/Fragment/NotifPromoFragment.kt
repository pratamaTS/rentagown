package com.example.rentagown.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NotifPromoAdapter
import com.example.rentagown.Model.NotifPromo
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class NotifPromoFragment : Fragment() {
    var rvNotifPromo: RecyclerView? = null
    var notifPromoList: ArrayList<NotifPromo>? = null
    var notifPromoAdapter: NotifPromoAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notif_promo, container, false)

        //INIT VIEW
        rvNotifPromo = view.findViewById(R.id.rv_notif_promo)

        //List
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )
        notifPromoList!!.add(
            NotifPromo(
                "17 AGT 2020",
                "Promo Spesial HUT RI Ke 71",
                "Diskon hingga 80%",
                "Dari style Prewedding produk terbaik dengan diskon hingga 80% lho! Cek sekarang, cuma di HUT RI ke 71 ini."
            )
        )

        //Setup Recycler View
        notifPromoAdapter = NotifPromoAdapter(context!!, notifPromoList!!)
        rvNotifPromo!!.setLayoutManager(LinearLayoutManager(activity))
        rvNotifPromo!!.setAdapter(notifPromoAdapter)
        return view
    }
}
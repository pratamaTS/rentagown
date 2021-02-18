package com.example.rentagown.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NotifPromoAdapter
import com.example.rentagown.Connection.Interface.GetNotifPromoInterface
import com.example.rentagown.Connection.Presenter.GetNotifPromoPresenter
import com.example.rentagown.Model.NotifPromo
import com.example.rentagown.R
import com.example.rentagown.Response.Notification.DataNotifPromo
import java.util.*
import kotlin.collections.ArrayList

class NotifPromoFragment : Fragment(), GetNotifPromoInterface {
    var rvNotifPromo: RecyclerView? = null
    var notifPromoList: ArrayList<DataNotifPromo> = ArrayList()
    var notifPromoAdapter: NotifPromoAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notif_promo, container, false)

        //INIT VIEW
        rvNotifPromo = view.findViewById(R.id.rv_notif_promo)

        getNotifPromo()

        return view
    }

    private fun getNotifPromo() {
        GetNotifPromoPresenter(this).getAllNotifPromo(context!!)
    }

    override fun onSuccessGetNotifPromo(dataNotifPromo: ArrayList<DataNotifPromo>?) {
        notifPromoList = dataNotifPromo as ArrayList<DataNotifPromo>

        //Setup Recycler View
        notifPromoAdapter = NotifPromoAdapter(context!!, notifPromoList)
        rvNotifPromo!!.setLayoutManager(LinearLayoutManager(activity))
        rvNotifPromo!!.setAdapter(notifPromoAdapter)
    }

    override fun onErrorGetNotifPromo(msg: String) {
        Toast.makeText(context, "Failed to get notification promo", Toast.LENGTH_SHORT)
    }
}
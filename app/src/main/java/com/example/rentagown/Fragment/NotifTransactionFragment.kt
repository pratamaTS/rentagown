package com.example.rentagown.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.MainAfterActivity
import com.example.rentagown.Adapter.NotifTransactionAdapter
import com.example.rentagown.Connection.Interface.GetNotificationInterface
import com.example.rentagown.Connection.Presenter.GetNotificationPresenter
import com.example.rentagown.R
import com.example.rentagown.Response.Notification.DataNotification
import java.util.*
import kotlin.collections.ArrayList


class NotifTransactionFragment : Fragment(), GetNotificationInterface {
    var rvNotifTransaction: RecyclerView? = null
    var notifTransactionList: ArrayList<DataNotification> = ArrayList()
    var notifTransactionAdapter: NotifTransactionAdapter? = null

    companion object {
        private const val READ_NOTIF = "read_notif"
        private const val STATUS_PAYMENT = "status_payment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notif_transaction, container, false)

        //INIT VIEW
        rvNotifTransaction = view.findViewById(R.id.rv_notif_transaction)

        getPublicMethod()

        getNotif()

        return view
    }

    private fun getPublicMethod() {
        val fragment = HomeFragment()
        var prefs: SharedPreferences = context!!.getSharedPreferences(
            context!!.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()

        fragment.setBadgeNotif(true)
        editor.putBoolean(READ_NOTIF, true)
        editor.commit()

        activity?.let { mAct ->
            // sekarang langsung akses bottom nav dulu, bisa diubah nanti kalo sudah diperbaiki
            if(mAct is MainActivity) {
                fragment.setBadgeNotif(true)
                editor.putBoolean(READ_NOTIF, true)
                editor.commit()
                Log.d("main act", "running")
                return
            }
            if(mAct is MainAfterActivity) {
                fragment.setBadgeNotif(true)
                editor.putBoolean(READ_NOTIF, true)
                editor.commit()
                Log.d("main after act", "running")
                return
            }
        }
    }

    private fun getNotif() {
        GetNotificationPresenter(this).getAllNotification(context!!)
    }

    override fun onSuccessGetNotification(dataNotification: ArrayList<DataNotification>?) {
        notifTransactionList = dataNotification as ArrayList<DataNotification>

        //Setup Recycler View
        notifTransactionAdapter = NotifTransactionAdapter(context!!, notifTransactionList!!)
        rvNotifTransaction!!.setLayoutManager(LinearLayoutManager(activity))
        rvNotifTransaction!!.setAdapter(notifTransactionAdapter)

    }

    override fun onErrorGetNotification(msg: String) {
        Toast.makeText(context, "Failed to get notification", Toast.LENGTH_SHORT)
    }
}
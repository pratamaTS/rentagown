package com.example.rentagown.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.rentagown.Activity.NotificationActivity
import com.example.rentagown.Fragment.NotifPromoFragment
import com.example.rentagown.Fragment.NotifTransactionFragment

class PageAdapterNotification(
    notificationActivity: NotificationActivity?,
    fm: FragmentManager?,
    var counttab: Int
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NotifTransactionFragment()
            }
            1 -> {
                NotifPromoFragment()
            }
            else -> NotifTransactionFragment()
        }
    }

    override fun getCount(): Int {
        return counttab
    }
}
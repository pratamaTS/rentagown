package com.example.rentagown.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Fragment.OverviewFragment
import com.example.rentagown.Fragment.ReviewFragment

class PageAdapterDetailProduct(
    viewProductActivity: ViewProductActivity?,
    fm: FragmentManager?,
    var counttab: Int
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OverviewFragment()
            }
            1 -> {
                ReviewFragment()
            }
            else -> OverviewFragment()
        }
    }

    override fun getCount(): Int {
        return counttab
    }
}
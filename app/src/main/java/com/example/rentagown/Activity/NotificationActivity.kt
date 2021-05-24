package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.rentagown.Adapter.PageAdapterNotification
import com.example.rentagown.R
import com.example.rentagown.v2.ui.choosedate.ChooseDateActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class NotificationActivity : AppCompatActivity(), View.OnClickListener {
    companion object {

        const val REQ_READ_NOTIFICATION = 1523

    }

    private lateinit var tabNotification: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        tabNotification = findViewById(R.id.tab_notification)
        viewPager = findViewById(R.id.vp_notification)

        //Set Tab Layout
        tabNotification.addTab(tabNotification.newTab().setText("Transaction"))
        tabNotification.addTab(tabNotification.newTab().setText("Promo"))

        val pageAdapterNotification = PageAdapterNotification(
            this,
            supportFragmentManager, tabNotification.tabCount
        )
        viewPager.adapter = pageAdapterNotification
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabNotification))
        tabNotification.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        //Set Listener
        back.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back ->
            {
                Intent().apply {
                    setResult(NotificationActivity.REQ_READ_NOTIFICATION, this)
                    finish()
                }
            }
        }
    }
}
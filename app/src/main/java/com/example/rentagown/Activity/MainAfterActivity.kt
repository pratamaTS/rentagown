package com.example.rentagown.Activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rentagown.Fragment.BookFragment
import com.example.rentagown.Fragment.HomeFragment
import com.example.rentagown.Fragment.ProfileAfterFragment
import com.example.rentagown.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainAfterActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    var frameLayout: FrameLayout? = null
    var selectedFragment: Fragment? = null
    var profileAfterFragment: ProfileAfterFragment = ProfileAfterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_after)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
            .commit()

        //INIT VIEW
        bottomNavigationView = findViewById(R.id.bot_nav)
        frameLayout = findViewById(R.id.fragment_container)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(bottomNavMethod)

    }

    private val bottomNavMethod =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    selectedFragment = HomeFragment()
                    bottomNavigationView!!.itemIconTintList = null
                }
                R.id.nav_booking -> {
                    selectedFragment = BookFragment()
                    bottomNavigationView!!.itemIconTintList = null
                }
                R.id.nav_profile -> {
                    selectedFragment = profileAfterFragment
                    bottomNavigationView!!.itemIconTintList = null
                }
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment!!
                ).commit()
            }
            true
        }
}
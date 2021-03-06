package com.example.rentagown.Activity

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rentagown.Fragment.HomeFragment
import com.example.rentagown.Fragment.LoginFragment
import com.example.rentagown.R
import com.example.rentagown.v2.ui.home.mybookings.MyBookingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var frameLayout: FrameLayout
    var selectedFragment: Fragment? = null
    var loginCheck: Boolean = false
    var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //INIT VIEW
        bottomNavigationView = findViewById(R.id.bot_nav)
        frameLayout = findViewById(R.id.fragment_container)
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod)
        if(intent?.hasExtra("login_check") == true) {
            loginCheck = intent?.getBooleanExtra("login_check", false) == true
            if(loginCheck == true) {
                val bundle: Bundle = Bundle()
                selectedFragment = LoginFragment()
                if(intent.hasExtra("message")){
                    message = intent.getStringExtra("message")
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }

                bottomNavigationView.selectedItemId = R.id.nav_profile
            }
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }
    }

    private val bottomNavMethod = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_home -> {
                selectedFragment = HomeFragment()
                bottomNavigationView.itemIconTintList = null
            }
            R.id.nav_booking -> {
                selectedFragment = MyBookingsFragment.newInstance()
                bottomNavigationView.itemIconTintList = null
            }
            R.id.nav_profile -> {
                selectedFragment = LoginFragment()
                bottomNavigationView.itemIconTintList = null
            }
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment!!).commit()
        }
        true
    }

    fun setSelectedNavId(navId: Int) {
        bottomNavigationView.selectedItemId = R.id.nav_home
    }

}
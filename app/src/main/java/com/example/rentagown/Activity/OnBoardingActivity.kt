package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.rentagown.Adapter.OnBoardingAdapter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Model.OnBoardingItem
import com.example.rentagown.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class OnBoardingActivity : AppCompatActivity() {
    private var screenPager: ViewPager? = null
    var onBoardingAdapter: OnBoardingAdapter? = null
    var tabIndicator: TabLayout? = null
    var btnNext: Button? = null
    var btnGetStarted: Button? = null
    var btnSkip: Button? = null
    var btnAnim: Animation? = null
    var position = 0
    var mList: MutableList<OnBoardingItem> = ArrayList<OnBoardingItem>()
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val sessionManager = SessionManager(this)

        //Init View
        screenPager = findViewById(R.id.screen_viewpager)
        tabIndicator = findViewById(R.id.tab_indicator)
        btnNext = findViewById(R.id.btn_next)
        btnGetStarted = findViewById(R.id.btn_get_started)
        btnSkip = findViewById(R.id.btn_skip)
        btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        //Fill list screen
        mList.add(
            OnBoardingItem(
                "Prewedding",
                "The chapter where the solitude ends and togetherness begins is about to start. " +
                        "Welcoming this chapter with special dress, sincere smiles, and loving memories.",
                R.drawable.bg_prewedding
            )
        )

        mList.add(
            OnBoardingItem(
                "Wedding",
                "We are here to help you find the perfect dress for the day that you will cherish for the rest of your life.",
                R.drawable.bg_wedding
            )
        )

        mList.add(
            OnBoardingItem(
                "Family",
                "Nothing is more important than making a wonderful memory with your loved ones. " +
                        "Find your stunning dress to make it even memorable.",
                R.drawable.bg_family
            )
        )

        mList.add(
            OnBoardingItem(
                "Maternity",
                "Wrapped yourself in magical dresses to welcome a grand adventure of your life.",
                R.drawable.bg_maternity
            )
        )

        mList.add(
            OnBoardingItem(
                "A more flexible way to rent",
                "We want to give our customers the best experience, and now we have made renting a dress way simpler and easier. ",
                R.drawable.bg_getstarted
            )
        )


        //Setup View Pager
        onBoardingAdapter = OnBoardingAdapter(this, mList as ArrayList<OnBoardingItem>)
        screenPager!!.setAdapter(onBoardingAdapter)

        //Setup TabLayout with ViewPager
        tabIndicator!!.setupWithViewPager(screenPager)

        //Next Button Click Listener
        btnNext!!.setOnClickListener(View.OnClickListener {
            position = screenPager!!.getCurrentItem()
            if (position < mList.size) {
                position++
                screenPager!!.setCurrentItem(position)
            }
            if (position == mList.size - 1) {

                //TODO : show the GET STARTED Button and hide next Button
                loadLastScreen()
            }
        })

        //Skip Button Click Listener
        btnSkip!!.setOnClickListener(View.OnClickListener {
            //ketika activiy ini akan di launch, kita perlu memeriksa apakah sudah dibuka sebelumnya atau tidak
            finishOnboarding()

            //                if (restorePredData()){
            //                    Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            //                    startActivity(mainActivity);
            //                    finish();
            //                }
        })

        //Get Started Button Click Listener
        btnGetStarted!!.setOnClickListener(View.OnClickListener { //also we need to save a boolean value to storage so next time when the user run the app
            //we could know that he is already checked the intro screen activity
            //use shared preferences to that process
            finishOnboarding()
            //                Intent main = new Intent(getApplicationContext(), MainActivity.class);
            //                startActivity(main);
            //                savePrefsData();
            finish()
        })
        tabIndicator!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == mList.size - 1) {
                    loadLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun finishOnboarding() {
        // Get the shared preferences
        val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        // Set onboarding_complete to true
        preferences.edit()
            .putBoolean("onboarding_complete", true).apply()

        // Launch the main Activity, called MainActivity
        if(token == null) {
            val main = Intent(this, MainActivity::class.java)
            startActivity(main)
        }else{
            val main = Intent(this, MainAfterActivity::class.java)
            startActivity(main)
        }
        // Close the OnboardingActivity
        finish()
    }

    //    private boolean restorePredData() {
    //        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
    //        return pref.getBoolean("isIntroOpened",false);
    //    }
    //
    private fun savePrefsData() {
        val preferences = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isIntroOpened", true)
        editor.apply()
    }

    //show the GET STARTED Button and hide next Button
    private fun loadLastScreen() {
        btnNext!!.visibility = View.INVISIBLE
        //        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted!!.visibility = View.VISIBLE

        //TODO: ADD an animation the get started button
        btnGetStarted!!.animation = btnAnim
    }
}
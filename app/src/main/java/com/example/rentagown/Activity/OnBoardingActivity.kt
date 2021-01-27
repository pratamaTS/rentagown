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
import com.example.rentagown.Model.OnBoardingItem
import com.example.rentagown.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlin.collections.ArrayList

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        //Init View
        screenPager = findViewById(R.id.screen_viewpager)
        tabIndicator = findViewById(R.id.tab_indicator)
        btnNext = findViewById(R.id.btn_next)
        btnGetStarted = findViewById(R.id.btn_get_started)
        btnSkip = findViewById(R.id.btn_skip)
        btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)


        //Fill list screen
        mList.add(
            OnBoardingItem(
                "Prewedding",
                "The special day that is coming soon will not be memorable without stunning clothes, " +
                        "we are here to bring that to you.",
                R.drawable.bg_prewedding
            )
        )
        mList.add(
            OnBoardingItem(
                "Wedding",
                "A dazzling collection available for you to use on your special day, creating unforgettable memories for a lifetime.",
                R.drawable.bg_wedding
            )
        )
        mList.add(
            OnBoardingItem(
                "Family",
                "Looking different is about showing inner beauty from another perspective, and that's what someone like you needs.",
                R.drawable.bg_family
            )
        )
        mList.add(
            OnBoardingItem(
                "Maternity",
                "Looking different is about showing inner beauty from another perspective, and that's what someone like you needs.",
                R.drawable.bg_maternity
            )
        )
        mList.add(
            OnBoardingItem(
                "A more flexible way to rent",
                "We are here to make it easy for you to choose clothes that are suitable for " +
                        "all your important activities so that you appear different from the others.",
                R.drawable.bg_get_started
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
        val main = Intent(this, MainActivity::class.java)
        startActivity(main)

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
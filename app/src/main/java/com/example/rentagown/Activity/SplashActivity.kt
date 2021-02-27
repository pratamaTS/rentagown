package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val onBoarding = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(onBoarding)
            finish()
        }, SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 1500
    }
}
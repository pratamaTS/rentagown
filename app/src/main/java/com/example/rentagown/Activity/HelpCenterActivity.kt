package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rentagown.R

class HelpCenterActivity : AppCompatActivity(), View.OnClickListener {
        var back: ImageButton? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_help_center)

            //INIT VIEW
            back = findViewById(R.id.im_back)


            //SET LISTENER
            back!!.setOnClickListener(this)
        }

        @SuppressLint("NonConstantResourceId")
        override fun onClick(v: View) {
            when (v.id) {
                R.id.im_back -> finish()
            }
        }
}
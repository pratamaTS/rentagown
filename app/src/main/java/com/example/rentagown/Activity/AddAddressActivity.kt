package com.example.rentagown.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R

class AddAddressActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        //INIT VIEW
        back = findViewById(R.id.im_back)

        //SET LISTENER
        back!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }
}
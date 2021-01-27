package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NewGownAdapter
import com.example.rentagown.Model.NewGown
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class NewGownActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvNewGown: RecyclerView? = null
    var newGownAdapter: NewGownAdapter? = null
    var newGownList: ArrayList<NewGown> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvNewGown = findViewById(R.id.rv_list_new_gown)

        //Slider New Gown
        newGownList = ArrayList()
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )

        //Setup Recycler View New Gown
        newGownAdapter = NewGownAdapter(this, newGownList)
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvNewGown?.setLayoutManager(gridLayoutManager)
        rvNewGown?.setAdapter(newGownAdapter)

        //SET LISTENER
        back?.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }
}
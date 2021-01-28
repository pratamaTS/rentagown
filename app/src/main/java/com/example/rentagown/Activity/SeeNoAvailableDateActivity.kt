package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NoAvailableDateAdapter
import com.example.rentagown.Model.NoAvailableDate
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class SeeNoAvailableDateActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvNoAvailableDate: RecyclerView? = null
    var availableDateAdapter: NoAvailableDateAdapter? = null
    var noAvailableDateList: ArrayList<NoAvailableDate> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_no_available_date)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvNoAvailableDate = findViewById(R.id.rv_no_available_date)

        //List NoAvailableDate
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))
        noAvailableDateList.add(NoAvailableDate("9 September 2021", "21 September 2021"))


        //Set up Adapter
        availableDateAdapter = NoAvailableDateAdapter(this, noAvailableDateList)
        rvNoAvailableDate?.setLayoutManager(LinearLayoutManager(this))
        rvNoAvailableDate?.setAdapter(availableDateAdapter)

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
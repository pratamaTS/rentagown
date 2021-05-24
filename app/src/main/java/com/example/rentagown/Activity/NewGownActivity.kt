package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.NewGownAdapter
import com.example.rentagown.Connection.Interface.NewGownInterface
import com.example.rentagown.Connection.Presenter.NewGownPresenter
import com.example.rentagown.Model.NewGown
import com.example.rentagown.R
import com.example.rentagown.Response.NewGown.DataNewGown
import java.util.*
import kotlin.collections.ArrayList

class NewGownActivity : AppCompatActivity(), View.OnClickListener, NewGownInterface {
    private lateinit var back: ImageButton
    private lateinit var rvNewGown: RecyclerView
    var newGownAdapter: NewGownAdapter? = null
    var newGownList: ArrayList<DataNewGown> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvNewGown = findViewById(R.id.rv_list_new_gown)

        getNewGown()

        //SET LISTENER
        back.setOnClickListener(this)
    }

    private fun getNewGown() {
        NewGownPresenter(this).getAllNewGown()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }

    override fun onSuccessGetNewGown(dataNewGown: ArrayList<DataNewGown>?) {

        Log.d("newgown", dataNewGown.toString())
        if(dataNewGown?.isNotEmpty() == true) {
            newGownList = dataNewGown

            //Setup Recycler View New Gown
            newGownAdapter = NewGownAdapter(this, newGownList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvNewGown.setLayoutManager(gridLayoutManager)
            rvNewGown.setAdapter(newGownAdapter)
        }
    }

    override fun onErrorGetNewGown(msg: String) {
        Toast.makeText(this, "Failed to get data new gown", Toast.LENGTH_SHORT)
    }

}
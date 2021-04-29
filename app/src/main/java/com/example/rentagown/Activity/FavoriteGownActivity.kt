package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.FavoriteGownAdapter
import com.example.rentagown.Connection.Interface.FavoriteGownInterface
import com.example.rentagown.Connection.Presenter.FavoriteGownPresenter
import com.example.rentagown.Model.FavoriteGown
import com.example.rentagown.R
import com.example.rentagown.Response.FavoriteGown.DataFavoriteGown
import com.example.rentagown.Response.Product.DataProduct
import java.util.*
import kotlin.collections.ArrayList

class FavoriteGownActivity : AppCompatActivity(), View.OnClickListener, FavoriteGownInterface {
    var back: ImageButton? = null
    var rvFavoriteGown: RecyclerView? = null
    var favoriteGownAdapter: FavoriteGownAdapter? = null
    var favoriteGownList: ArrayList<DataFavoriteGown> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvFavoriteGown = findViewById(R.id.rv_list_favorite_gown)

        getFavoriteGown()

        //SET LISTENER
        back?.setOnClickListener(this)
    }

    private fun getFavoriteGown() {
        FavoriteGownPresenter(this).getAllFavoriteGown()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }

    override fun onSuccessGetFavoriteGown(dataFavoriteGown: ArrayList<DataFavoriteGown?>?) {
        if(dataFavoriteGown?.isNotEmpty() == true) {
            favoriteGownList = dataFavoriteGown as ArrayList<DataFavoriteGown>

            //Setup Recycler View Favorite
            favoriteGownAdapter = FavoriteGownAdapter(this, favoriteGownList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvFavoriteGown?.setLayoutManager(gridLayoutManager)
            rvFavoriteGown?.setAdapter(favoriteGownAdapter)
        }
    }

    override fun onErrorGetFavoriteGown(msg: String) {
        Toast.makeText(this, "Failed to get data favorite gown", Toast.LENGTH_SHORT)
    }
}
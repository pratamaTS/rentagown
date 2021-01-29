package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.FavoriteGownAdapter
import com.example.rentagown.Model.FavoriteGown
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class FavoriteGownActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvFavoriteGown: RecyclerView? = null
    var favoriteGownAdapter: FavoriteGownAdapter? = null
    var favoriteGownList: ArrayList<FavoriteGown> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvFavoriteGown = findViewById(R.id.rv_list_favorite_gown)

        //List Product Favorite
//        favoriteGownList = ArrayList()
//        favoriteGownList.add(
//            FavoriteGown(
//                R.drawable.product_favourite,
//                "Dahlia Cascade Layered Jumpsuit",
//                "Rp. 12.000.000"
//            )
//        )

        //Setup Recycler View Favorite
        favoriteGownAdapter = FavoriteGownAdapter(this, favoriteGownList)
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvFavoriteGown?.setLayoutManager(gridLayoutManager)
        rvFavoriteGown?.setAdapter(favoriteGownAdapter)

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
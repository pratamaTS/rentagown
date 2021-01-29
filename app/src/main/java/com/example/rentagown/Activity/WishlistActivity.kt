package com.example.rentagown.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.WishlistAdapter
import com.example.rentagown.Model.Wishlist
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class WishlistActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var rvWishlist: RecyclerView? = null
    var layoutEmpty: LinearLayout? = null
    var wishlistList: ArrayList<Wishlist>? = null
    var wishlistAdapter: WishlistAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvWishlist = findViewById(R.id.rv_wishlist)
        layoutEmpty = findViewById(R.id.layout_wishlist_empty)

        //List Product Wishlist

        //Setup Adapter
        wishlistAdapter = WishlistAdapter(this, wishlistList!!)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvWishlist!!.setLayoutManager(gridLayoutManager)
        rvWishlist!!.setAdapter(wishlistAdapter)
        if (wishlistAdapter!!.getItemCount() > 0) {
            layoutEmpty!!.setVisibility(View.GONE)
        }

        //SET LISTENER
        back!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }
}
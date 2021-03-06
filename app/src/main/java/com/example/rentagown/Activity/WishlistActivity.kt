package com.example.rentagown.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.WishlistAdapter
import com.example.rentagown.Connection.Interface.GetWishlistInterface
import com.example.rentagown.Connection.Presenter.GetWishlistPresenter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Model.Wishlist
import com.example.rentagown.R
import com.example.rentagown.Response.GetWishlist.DataWishlist
import com.example.rentagown.v2.ui.home.mybookings.MyBookingsContract
import java.util.*
import kotlin.collections.ArrayList

class WishlistActivity : AppCompatActivity(), View.OnClickListener, GetWishlistInterface {
    private lateinit var back: ImageButton
    private lateinit var rvWishlist: RecyclerView
    private lateinit var layoutEmpty: LinearLayout
    var wishlistList: ArrayList<DataWishlist> = ArrayList()
    var wishlistAdapter: WishlistAdapter? = null
    private lateinit var btnBrowse: Button
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        val sessionManager = SessionManager(this)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        rvWishlist = findViewById(R.id.rv_wishlist)
        layoutEmpty = findViewById(R.id.layout_wishlist_empty)
        btnBrowse = findViewById(R.id.btn_browse)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        getWishlist()

        //SET LISTENER
        back.setOnClickListener(this)
        btnBrowse.setOnClickListener(this)
    }

    private fun getWishlist() {
        GetWishlistPresenter(this).getAllWishlist(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_browse -> {
                if(token != null) {
//                    MainAfterActivity().setSelectedNavId(R.id.nav_home)
//                    return
                    onBackPressed()
                }else {
                    onBackPressed()
                }
            }
        }
}

    override fun onSuccessGetWishlist(dataWishlist: ArrayList<DataWishlist>?) {
        if(dataWishlist?.isNotEmpty() == true) {
            wishlistList = dataWishlist as ArrayList<DataWishlist>

            //Setup Adapter
            wishlistAdapter = WishlistAdapter(this, wishlistList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
            rvWishlist.setLayoutManager(gridLayoutManager)
            rvWishlist.setAdapter(wishlistAdapter)
            if (wishlistAdapter!!.getItemCount() > 0) {
                layoutEmpty.setVisibility(View.GONE)
            }
        }else{
            layoutEmpty.setVisibility(View.VISIBLE)
        }
    }

    override fun onErrorGetWishlist(msg: String) {
        Toast.makeText(this, "Failed to get data wishist", Toast.LENGTH_SHORT)
    }
}
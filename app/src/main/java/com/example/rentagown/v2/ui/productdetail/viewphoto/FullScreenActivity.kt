package com.example.rentagown.v2.ui.productdetail.viewphoto

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.rentagown.Body.WishlistBody
import com.example.rentagown.Connection.Interface.AddWishlistInterface
import com.example.rentagown.Connection.Presenter.AddWishlistPresenter
import com.example.rentagown.Fragment.LoginFragment
import com.example.rentagown.Model.SliderItemProduct
import com.example.rentagown.R
import com.example.rentagown.Response.CreateWishlist.DataAddWishlist
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductImage
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bookingcart.BookingCartActivity
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.example.rentagown.v2.ui.productdetail.ProductDetailContract
import com.example.rentagown.v2.ui.productdetail.ProductDetailPresenter
import com.example.rentagown.v2.ui.productdetail.adapter.ProductDetailPagerAdapter
import com.example.rentagown.v2.ui.productdetail.adapter.ProductImageAdapter
import com.example.rentagown.v2.ui.productdetail.adapter.SliderViewFullScreenAdapter
import com.example.rentagown.v2.ui.productdetail.productoverview.ProductOverviewContract
import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.SliderView
import java.util.*

class FullScreenActivity : BaseRAGActivity<FullScreenContract.Presenter>(), FullScreenContract.View,
    AddWishlistInterface, View.OnClickListener, LoginFragment.Listener {

    override val layoutId: Int = R.layout.activity_fullscreen
    override lateinit var presenter: FullScreenContract.Presenter

    private lateinit var rootView: View
    private lateinit var baseContainer: View
    private lateinit var fragmentContainer: View

    private lateinit var sliderViewFullScreenAdapter: SliderViewFullScreenAdapter

    private lateinit var btnClose: ImageButton

    private lateinit var sliderView: SliderView
    private lateinit var btnAddtoWishlist: Button

    override fun init() {
        super.init()

        presenter = FullScreenPresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(RAGApi.apiService(this)))
                .productRepository)

    }

    override fun setupWidgets() {
        super.setupWidgets()

        rootView = findViewById(R.id.root_view)
        fragmentContainer = findViewById(R.id.fragment_container)

        sliderView = findViewById(R.id.slider_view_fullscreen)
        btnClose = findViewById(R.id.im_close)
        btnAddtoWishlist = findViewById(R.id.btn_add_wishlist)

        btnClose.setOnClickListener(this)
        btnAddtoWishlist.setOnClickListener(this)

    }

    override fun setupAdapter() {
        super.setupAdapter()

        sliderViewFullScreenAdapter = SliderViewFullScreenAdapter(arrayListOf())
        sliderView.setSliderAdapter(sliderViewFullScreenAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSelectedProductId()?.let { productId ->
            presenter.getProductDetail(productId)
        }
    }

    override fun showProductImages(images: List<ProductImage>) {
        sliderViewFullScreenAdapter.replaceData(images.map { pi -> pi.photoPath })
    }

    override fun getSelectedProductId(): String? = intent.getStringExtra("product_id")
    override fun getSelectedCategory(): String? = intent.getStringExtra("category")

    override fun isUserLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    override fun navigateToLogin() {

        val f = LoginFragment.newInstance(this)
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer.id, f, LoginFragment.TAG)
            .addToBackStack(LoginFragment.TAG)
            .commit()
    }

    override fun showBaseContent(popBackStack: Boolean) {
        if(popBackStack) {
            val f = supportFragmentManager.fragments.indexOfFirst { f -> f is LoginFragment }
            if(f >= 0) {
                supportFragmentManager.popBackStack(LoginFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    override fun setResultBookingCreated() {
        setResult(ProductDetailActivity.RES_BOOKING_CREATED)
    }

    override fun addToWishlist() {
        var productID = ""
        getSelectedProductId()?.apply { productID = this }
        AddWishlistPresenter(this).addWishlist(this, WishlistBody(productID))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.im_close -> closeView()
            R.id.btn_add_wishlist -> presenter.onBtnAddToWishlistClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.fragments.indexOfFirst { f -> f is LoginFragment }
        if(f >= 0) {
            supportFragmentManager.popBackStack(LoginFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            showBaseContent(false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onLoggedIn() {
        presenter.onUserLoggedIn()
    }

    override fun onSuccessAddWishlist(dataAddWishlist: DataAddWishlist) {
        when(dataAddWishlist.isWishlist){
            0 -> {
                Toast.makeText(this, "Remove from wishlist", Toast.LENGTH_SHORT).show()
                closeView()
            }
            1 -> {
                Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show()
                closeView()
            }
        }

    }

    override fun onErrorAddWishlist(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
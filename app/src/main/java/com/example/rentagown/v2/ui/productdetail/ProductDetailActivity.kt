package com.example.rentagown.v2.ui.productdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.rentagown.Fragment.LoginFragment
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductImage
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.booking.BookingActivity
import com.example.rentagown.v2.ui.bookingcart.BookingCartActivity
import com.example.rentagown.v2.ui.productdetail.adapter.ProductDetailPagerAdapter
import com.example.rentagown.v2.ui.productdetail.adapter.ProductImageAdapter
import com.example.rentagown.v2.ui.productdetail.productoverview.ProductOverviewContract
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.SliderView
import java.util.*

class ProductDetailActivity : BaseRAGActivity<ProductDetailContract.Presenter>(), ProductDetailContract.View,
        View.OnClickListener, LoginFragment.Listener {

    companion object {
        const val REQ_VIEW_PRODUCT_DETAIL = 471
        const val RES_BOOKING_CREATED = 472
    }

    override val layoutId: Int = R.layout.activity_product_detail_v2

    override lateinit var presenter: ProductDetailContract.Presenter

    private lateinit var productDetailPagerAdapter: ProductDetailPagerAdapter
    private lateinit var productImageAdapter: ProductImageAdapter

    override var btnBackId: Int = R.id.btn_back

    private lateinit var rootView: View
    private lateinit var baseContainer: View
    private lateinit var fragmentContainer: View

    private lateinit var tlProductDetail: TabLayout
    private lateinit var vpProductDetail: ViewPager2
    private lateinit var svProductImages: SliderView

    private lateinit var btnWhatsapp: ImageButton
    private lateinit var btnBookNow: Button


    override fun init() {
        super.init()

        presenter = ProductDetailPresenter(
            RepositoryLocator
            .getInstance(
                RemoteRepositoryLocator
                .getInstance(RAGApi.apiService(this)))
            .productRepository)

    }

    override fun setupWidgets() {
        super.setupWidgets()

        rootView = findViewById(R.id.root_view)
        baseContainer = findViewById(R.id.base_container)
        fragmentContainer = findViewById(R.id.fragment_container)

        tlProductDetail = findViewById(R.id.tl_product_details)
        vpProductDetail = findViewById(R.id.vp_product_detail)
        svProductImages = findViewById(R.id.sv_product_images)
        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnBookNow = findViewById(R.id.btn_book_now)


        btnWhatsapp.setOnClickListener(this)
        btnBookNow.setOnClickListener(this)

    }

    override fun setupAdapter() {
        super.setupAdapter()

        productDetailPagerAdapter = ProductDetailPagerAdapter(this, getSelectedProductId(), getSelectedCategory(), getSelectedProduct())

        vpProductDetail.isUserInputEnabled = false
        vpProductDetail.adapter = productDetailPagerAdapter
        vpProductDetail.offscreenPageLimit = 2

        TabLayoutMediator(tlProductDetail, vpProductDetail) { tab, position ->
            when (position) {
                0 -> tab.text = "Overview"
                1 -> tab.text = "Reviews"
            }
        }.attach()

        productImageAdapter = ProductImageAdapter(this, getSelectedProductId().toString(), arrayListOf())
        svProductImages.setSliderAdapter(productImageAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSelectedProductId()?.let { productId ->
            presenter.getProductDetail(productId)
        }
    }

    override fun showProductImages(images: List<ProductImage>) {
        productImageAdapter.replaceData(images.map { pi -> pi.photoPath })
    }

    override fun getSelectedProductId(): String? = intent.getStringExtra("product_id")
    override fun getSelectedCategory(): String? = intent.getStringExtra("category")

    override fun isUserLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    override fun navigateToLogin() {
        baseContainer.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

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

        fragmentContainer.visibility = View.GONE
        baseContainer.visibility = View.VISIBLE
    }

    override fun showMsgBookingDateNotValid() {
        showMessage(getString(R.string.err_booking_date_not_valid))
    }

    override fun setResultBookingCreated() {
        setResult(RES_BOOKING_CREATED)
    }

    override fun getUserPhoneNumber() = sessionManager.fetchPhoneNumber()

    override fun navigateToWhatsapp(phoneNumber: String) {
        val url = "https://api.whatsapp.com/send/?phone=$phoneNumber"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    override fun getBookingDates(): Pair<Calendar?, Calendar?> {
        supportFragmentManager.fragments.forEach {
            if(it is ProductOverviewContract.View) {
                return it.getBookingDatesValue()
            }
        }

        return Pair(null, null)
    }

    override fun getSpecialTreatmentSelected(): Boolean {
        supportFragmentManager.fragments.forEach {
            if(it is ProductOverviewContract.View) {
                return it.getSpecialTreatmentSelected()
            }
        }

        return false
    }

    override fun navigateToCart(createBooking: ReqCreateBooking, product: Product?) {
        Intent(this, BookingCartActivity::class.java).apply {
            putExtra("req_create_booking", createBooking)
            putExtra("product", product)

            startActivityForResult(this, BookingCartActivity.REQ_CREATE_BOOKING)
        }
    }

    override fun setProductDataToView(product: Product) {
        supportFragmentManager.fragments.forEach { f ->
            if(f is ProductOverviewContract.View) {
                f.setProductDataToView(product)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_whatsapp -> presenter.onBtnWhatsapClicked()
            R.id.btn_book_now -> presenter.onBtnBookNowClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == BookingCartActivity.REQ_CREATE_BOOKING) {
            if(resultCode == BookingCartActivity.RES_BOOKING_CREATED) {
                presenter.onBookingCreated()
            }
        } else if(requestCode == REQ_VIEW_PRODUCT_DETAIL) {
            if(resultCode == RES_BOOKING_CREATED) {
                presenter.onBookingCreated()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun getSelectedProduct(): Product? = intent.getParcelableExtra("product")

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


}
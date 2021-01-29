package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager.widget.ViewPager
import com.example.rentagown.Adapter.PageAdapterDetailProduct
import com.example.rentagown.Adapter.SliderViewProductAdapter
import com.example.rentagown.Connection.Interface.DetailProductInterface
import com.example.rentagown.Connection.Presenter.DetailProductPresenter
import com.example.rentagown.Model.SliderItemProduct
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataDetailProduct
import com.example.rentagown.Response.Product.DataPhoto
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.*
import kotlin.collections.ArrayList

class ViewProductActivity : AppCompatActivity(), View.OnClickListener, DetailProductInterface {
    var sliderViewProductAdapter: SliderViewProductAdapter? = null
    var sliderView: SliderView? = null
    var sliderItemProductList: ArrayList<SliderItemProduct> = ArrayList()
    var detailProduct: DataDetailProduct? = null
    var photoItem: ArrayList<DataPhoto> = ArrayList()
    var tabDetailProduct: TabLayout? = null
    var back: ImageButton? = null
    var btnWhatsapp: ImageButton? = null
    var btnBookNow: Button? = null
    var bottomSheet: LinearLayout? = null
    var idProduct: String? = null
    var viewPager: ViewPager? = null
    var token: String ? = null

    //tambahan variable
    var containerViewProduct: CoordinatorLayout? = null
    var behavior: BottomSheetBehavior<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        //INIT VIEW
        containerViewProduct = findViewById(R.id.containerActivityViewProduct)
        sliderView = findViewById(R.id.slider_view_product)
        tabDetailProduct = findViewById(R.id.tab_detail_product)
        back = findViewById(R.id.im_back)
        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnBookNow = findViewById(R.id.btn_book_now)
        bottomSheet = findViewById(R.id.bottom_sheet)
        idProduct = intent.getStringExtra("id_product")
        viewPager = findViewById(R.id.vp_detail_product)

//        BottomSheetUtils.setupViewPager(viewPager);

        //untuk membuat bottom sheet dinamis
        setupBottomSheet()

        getDetailProduct()

        //Set Listener
        back!!.setOnClickListener(this)
        btnWhatsapp!!.setOnClickListener(this)
        btnBookNow!!.setOnClickListener(this)

        //ditambahkan ini
        bottomSheet?.requestLayout()
    }

    private fun setupBottomSheet() {
        behavior = BottomSheetBehavior.from(bottomSheet!!)

        //kita cari dulu panjang dari sliderviewnya
        sliderView!!.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                sliderView!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val sliderViewHeight = sliderView!!.height

                //habis itu kita cari dulu panjang dari containernya
                containerViewProduct!!.viewTreeObserver.addOnGlobalLayoutListener(object :
                    OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        containerViewProduct!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        val containerHeight = containerViewProduct!!.height
                        behavior?.setPeekHeight(containerHeight - sliderViewHeight + 100)
                    }
                })
            }
        })
    }

    private fun getDetailProduct() {
        DetailProductPresenter(this).getDetailProduct(idProduct.toString())
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_book_now -> {
                if(token != null) {
                    val bookNow = Intent(this@ViewProductActivity, YourBookingActivity::class.java)
                    startActivity(bookNow)
                }else{
                    val mainActivity = Intent(this, MainActivity::class.java)
                    mainActivity.putExtra("login_check", true)
                    startActivity(mainActivity)
                }
            }
            R.id.btn_whatsapp -> {
                val number = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$number"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }

    override fun onSuccessGetDetailProduct(dataDetailProduct: DataDetailProduct?) {
        detailProduct = dataDetailProduct

        //get photo
        photoItem = detailProduct!!.photo!!

        //Setup adapter
        sliderViewProductAdapter = SliderViewProductAdapter(this, photoItem)
        sliderView!!.setSliderAdapter(sliderViewProductAdapter!!)

        val params = viewPager!!.layoutParams
        params.height = 5000
        viewPager!!.layoutParams = params
        sliderView!!.setIndicatorAnimation(IndicatorAnimationType.SLIDE)
        sliderView!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)

        //Set Tab Layout
        tabDetailProduct!!.addTab(tabDetailProduct?.newTab()!!.setText("Overview"))
        tabDetailProduct!!.addTab(tabDetailProduct?.newTab()!!.setText("Review"))
        val pageAdapterDetailProduct = PageAdapterDetailProduct(
            this,
            supportFragmentManager, tabDetailProduct!!.getTabCount(),
            detailProduct
        )
        viewPager!!.adapter = pageAdapterDetailProduct
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabDetailProduct))
        tabDetailProduct!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    override fun onErrorGetDetailProduct(msg: String) {
        Toast.makeText(this, "Failed to get data detail product", Toast.LENGTH_SHORT)
    }
}
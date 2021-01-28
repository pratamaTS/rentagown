package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akexorcist.roundcornerprogressbar.CenteredRoundCornerProgressBar
import com.akexorcist.roundcornerprogressbar.indeterminate.IndeterminateCenteredRoundCornerProgressBar
import com.example.rentagown.Activity.*
import com.example.rentagown.Adapter.*
import com.example.rentagown.Connection.Interface.*
import com.example.rentagown.Connection.Presenter.*
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.*
import com.example.rentagown.R
import com.example.rentagown.Response.FavoriteGown.DataFavoriteGown
import com.example.rentagown.Response.NewGown.DataNewGown
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.Response.ProductCategory.DataProductCategory
import com.example.rentagown.Response.Promo.DataPromo
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), View.OnClickListener,
    ItemClickListener, ProductCategoryInterface, ProductByCategoryInterface, PromoInterface, FavoriteGownInterface, NewGownInterface, CategoryMenuAdapter.ReloadItemInterface {
    var imWishlist: ImageButton? = null
    var imNotification: ImageButton? = null
    var btnSeeAllCategory: Button? = null
    var btnSeeAllPromo: Button? = null
    var btnSeeAllFavoriteGown: Button? = null
    var btnSeeAllNewGown: Button? = null
    var rvTitleMenu: RecyclerView? = null
    var rvSliderMenu: RecyclerView? = null
    var rvSliderPromo: RecyclerView? = null
    var layoutPromoEmpty: ImageView? = null
    var searchView: SearchView? = null
    var layoutPromo: ConstraintLayout? = null
    var rvSliderFavoriteGown: RecyclerView? = null
    var rvSliderNewGown: RecyclerView? = null
    var adapterMenu: CategoryMenuAdapter? = null
    var adapterMainMenu: SliderMainMenuAdapter? = null
    var adapterPromo: SliderPromoAdapter? = null
    var adapterFavoriteGown: SliderFavoriteGownAdapter? = null
    var adapterNewGown: SliderNewGownAdapter? = null
    var categoryMenuList: ArrayList<DataProductCategory>? = null
    var sliderMainMenuList: ArrayList<DataProduct>? = null
    var promoList: ArrayList<DataPromo> = ArrayList()
    var favoriteGownList: ArrayList<DataFavoriteGown> = ArrayList()
    var newGownList: ArrayList<DataNewGown>?= null
    var dummySliderMainMenus: ArrayList<DataProduct> = ArrayList()
    var itemDecorSet: Boolean = false
    var tvvNoItemHome: TextView? = null
    var swipeRefreshHome: SwipeRefreshLayout? = null
    var pbProcat: IndeterminateCenteredRoundCornerProgressBar? = null
    var pbPromo: IndeterminateCenteredRoundCornerProgressBar? = null
    var pbFavGown: IndeterminateCenteredRoundCornerProgressBar? = null
    var pbNewGown: IndeterminateCenteredRoundCornerProgressBar? = null

    private var selectedCategoryMenu: DataProductCategory? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        //INIT VIEW
        imWishlist = view.findViewById(R.id.im_wishlist)
        imNotification = view.findViewById(R.id.im_notification)
        layoutPromoEmpty = view.findViewById(R.id.layout_promo_empty)
        layoutPromo = view.findViewById(R.id.layout_promo)
        btnSeeAllCategory = view.findViewById(R.id.btn_see_all_category)
        btnSeeAllPromo = view.findViewById(R.id.btn_see_all_promo)
        searchView = view.findViewById(R.id.search_view)
        btnSeeAllFavoriteGown = view.findViewById(R.id.btn_see_all_favorite)
        btnSeeAllNewGown = view.findViewById(R.id.btn_see_all_new)
        rvTitleMenu = view.findViewById(R.id.rv_title_menu)
        rvSliderMenu = view.findViewById(R.id.rv_slider_main_menu)
        rvSliderPromo = view.findViewById(R.id.rv_slider_promo)
        rvSliderFavoriteGown = view.findViewById(R.id.rv_slider_favorite_gown)
        rvSliderNewGown = view.findViewById(R.id.rv_slider_new_gown)
        swipeRefreshHome = view.findViewById(R.id.swipe_refresh_home)
        pbProcat = view.findViewById(R.id.pb_procat)
        pbPromo = view.findViewById(R.id.pb_promo)
        pbFavGown = view.findViewById(R.id.pb_fav_gown)
        pbNewGown = view.findViewById(R.id.pb_new_gown)

        swipeRefreshHome!!.setOnRefreshListener {
            getData()
            swipeRefreshHome!!.isRefreshing = false
        }

        getData()

        imWishlist!!.setOnClickListener(this@HomeFragment)
        imNotification!!.setOnClickListener(this@HomeFragment)
        btnSeeAllCategory!!.setOnClickListener(this@HomeFragment)
        searchView!!.setOnClickListener(this@HomeFragment)

        return view
    }

    private fun getData(){
        ProductCategoryPresenter(this).getAllProductCategory()
        PromoPresenter(this).getAllPromo()
        FavoriteGownPresenter(this).getAllFavoriteGown()
        NewGownPresenter(this).getAllNewGown()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.search_view -> {
                val search = Intent(activity, SearchViewActivity::class.java)
                startActivity(search)
            }
            R.id.im_wishlist -> {
                val wishlist = Intent(activity, WishlistActivity::class.java)
                startActivity(wishlist)
            }
            R.id.im_notification -> {
                val notification = Intent(activity, NotificationActivity::class.java)
                startActivity(notification)
            }
            R.id.btn_see_all_category -> {
                val categoryProduct = Intent(activity, ProductGownActivity::class.java)
                startActivity(categoryProduct)
            }
            R.id.btn_see_all_favorite -> {
                val favoriteGown = Intent(activity, FavoriteGownActivity::class.java)
                startActivity(favoriteGown)
            }
            R.id.btn_see_all_new -> {
                val newGown = Intent(activity, NewGownActivity::class.java)
                startActivity(newGown)
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        when (view!!.id) {
            R.id.category_menu_item_container -> {
                val cm = adapterMenu!!.getItem(position)
                adapterMainMenu!!.replaceItems(sliderMainMenuList!!)
            }
        }
    }

    override fun onSuccessGetProductCategory(dataProductCat: ArrayList<DataProductCategory>?) {

        //Title Menu
        categoryMenuList = dataProductCat

        if(categoryMenuList?.isNotEmpty() == true) {
            //Bind Item to Adapter
            adapterMenu = CategoryMenuAdapter(this, categoryMenuList!!, this)
            rvTitleMenu!!.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvTitleMenu!!.setAdapter(adapterMenu)
            rvTitleMenu!!.addItemDecoration(ItemDecorationSlider(16))
            adapterMenu!!.selectCategory(0)

            // preselect first category
            selectedCategoryMenu = adapterMenu!!.getItem(0)


            //Slider Main Menu
            ProductByCategoryPresenter(this).getAllProductByCategory(selectedCategoryMenu!!.nameProductCategory.toString())
        } else{
            Toast.makeText(context, "There is no category data", Toast.LENGTH_SHORT)
        }
    }

    override fun onErrorGetProductCategory(msg: String) {
        Toast.makeText(context, "Failed to get data category", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {

        pbProcat!!.visibility = View.GONE
        sliderMainMenuList = dataProductByCat
        rvSliderMenu!!.visibility = View.VISIBLE

        //Setup Recycler View Slider Main Menu
        if(dataProductByCat?.isNotEmpty() == true) {
            rvSliderMenu!!.visibility = View.VISIBLE
            adapterMainMenu = sliderMainMenuList?.let { SliderMainMenuAdapter(context!!, it) }
            rvSliderMenu!!.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvSliderMenu!!.setAdapter(adapterMainMenu!!)
            if (itemDecorSet == false) {
                rvSliderMenu!!.addItemDecoration(ItemDecorationSlider(16))
                itemDecorSet = true
            }
        }else{
            rvSliderMenu!!.visibility = View.GONE
        }
    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(context, "Failed to get data product", Toast.LENGTH_SHORT)
    }

    override fun passReloadItem(namaCategory: String) {
        ProductByCategoryPresenter(this).getAllProductByCategory(namaCategory)
    }

    override fun onSuccessGetNewGown(dataNewGown: ArrayList<DataNewGown?>?) {
        //Slider New Gown
        pbNewGown!!.visibility = View.GONE
        newGownList = dataNewGown as ArrayList<DataNewGown>
        rvSliderNewGown!!.visibility = View.VISIBLE

        //Setup Recycler View New Gown
        adapterNewGown = SliderNewGownAdapter(context!!, newGownList!!)
        rvSliderNewGown!!.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvSliderNewGown!!.setAdapter(adapterNewGown)
        rvSliderNewGown!!.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.HORIZONTAL
            )
        )
    }

    override fun onErrorGetNewGown(msg: String) {
        Toast.makeText(context, "Failed to get data new gown", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetFavoriteGown(dataFavoriteGown: ArrayList<DataFavoriteGown?>?) {
        //Slider Favorite Gown
        pbFavGown!!.visibility = View.GONE
        favoriteGownList = dataFavoriteGown as ArrayList<DataFavoriteGown>
        rvSliderFavoriteGown!!.visibility = View.VISIBLE

        //Setup Recycler View Favorite Gown
        adapterFavoriteGown = SliderFavoriteGownAdapter(context!!, favoriteGownList)
        rvSliderFavoriteGown!!.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvSliderFavoriteGown!!.setAdapter(adapterFavoriteGown)
        rvSliderFavoriteGown!!.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.HORIZONTAL
            )
        )

    }

    override fun onErrorGetFavoriteGown(msg: String) {
        Toast.makeText(context, "Failed to get data favorite gown", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetPromo(dataPromo: ArrayList<DataPromo>?) {
        pbPromo!!.visibility = View.GONE
        promoList = dataPromo as ArrayList<DataPromo>
        rvSliderPromo!!.visibility = View.VISIBLE

        if(promoList.isEmpty()){
            layoutPromoEmpty?.setVisibility(View.GONE)
            layoutPromo?.setVisibility(View.VISIBLE)
        }else {
            //Setup Recycler View Promo
            adapterPromo = SliderPromoAdapter(context!!, promoList)
            rvSliderPromo!!.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvSliderPromo!!.setAdapter(adapterPromo)
            rvSliderPromo!!.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.HORIZONTAL
                )
            )
        }
    }

    override fun onErrorGetPromo(msg: String) {
        Toast.makeText(context, "Failed to get data promo", Toast.LENGTH_SHORT)
    }
}
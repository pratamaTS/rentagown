package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.NotificationActivity
import com.example.rentagown.Activity.ProductGownActivity
import com.example.rentagown.Activity.WishlistActivity
import com.example.rentagown.Adapter.*
import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Interface.ProductCategoryInterface
import com.example.rentagown.Connection.Presenter.ProductByCategoryPresenter
import com.example.rentagown.Connection.Presenter.ProductCategoryPresenter
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.*
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.Response.ProductCategory.DataProductCategory
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), View.OnClickListener,
    ItemClickListener, ProductCategoryInterface, ProductByCategoryInterface, CategoryMenuAdapter.ReloadItemInterface {
    var imWishlist: ImageButton? = null
    var imNotification: ImageButton? = null
    var btnSeeAllCategory: Button? = null
    var btnSeeAllPromo: Button? = null
    var btnSeeAllFavoriteGown: Button? = null
    var btnSeeAllNewGown: Button? = null
    var rvTitleMenu: RecyclerView? = null
    var rvSliderMenu: RecyclerView? = null
    var rvSliderPromo: RecyclerView? = null
    var rvSliderFavoriteGown: RecyclerView? = null
    var rvSliderNewGown: RecyclerView? = null
    var adapterMenu: CategoryMenuAdapter? = null
    var adapterMainMenu: SliderMainMenuAdapter? = null
    var adapterPromo: SliderPromoAdapter? = null
    var adapterFavoriteGown: SliderFavoriteGownAdapter? = null
    var adapterNewGown: SliderNewGownAdapter? = null
    var categoryMenuList: ArrayList<DataProductCategory> = ArrayList()
    var sliderMainMenuList: ArrayList<DataProduct> = ArrayList()
    var promoList: ArrayList<Promo> = ArrayList()
    var favoriteGownList: ArrayList<FavoriteGown> = ArrayList()
    var newGownList: ArrayList<NewGown> = ArrayList()
    var dummySliderMainMenus: ArrayList<DataProduct> = ArrayList()
    var itemDecorSet: Boolean = false
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
        btnSeeAllCategory = view.findViewById(R.id.btn_see_all_category)
        btnSeeAllPromo = view.findViewById(R.id.btn_see_all_promo)
        btnSeeAllFavoriteGown = view.findViewById(R.id.btn_see_all_favorite)
        btnSeeAllNewGown = view.findViewById(R.id.btn_see_all_new)
        rvTitleMenu = view.findViewById(R.id.rv_title_menu)
        rvSliderMenu = view.findViewById(R.id.rv_slider_main_menu)
        rvSliderPromo = view.findViewById(R.id.rv_slider_promo)
        rvSliderFavoriteGown = view.findViewById(R.id.rv_slider_favorite_gown)
        rvSliderNewGown = view.findViewById(R.id.rv_slider_new_gown)

        getProductCategory()

        //Slider Promo]
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )
        promoList.add(
            Promo(
                R.drawable.promo,
                "Promo",
                "The wait is over Massive sale! 90% off for third purchase on any dress (except wedding dress), " +
                        "Booking period until 15 September 2020. Rental period until December 2021."
            )
        )

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


        //Slider Favorite Gown
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )
        favoriteGownList.add(
            FavoriteGown(
                R.drawable.product_favourite,
                "Dahlia Cascade Layered Jumpsuit",
                "Rp. 12.000.000"
            )
        )

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

        //Slider New Gown
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )
        newGownList.add(
            NewGown(
                R.drawable.new_product,
                "Nude Embellishment Mermaid Gown",
                "Rp. 4.000.000"
            )
        )

        //Setup Recycler View New Gown
        adapterNewGown = SliderNewGownAdapter(context!!, newGownList)
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
        imWishlist!!.setOnClickListener(this@HomeFragment)
        imNotification!!.setOnClickListener(this@HomeFragment)
        btnSeeAllCategory!!.setOnClickListener(this@HomeFragment)
        return view
    }

    private fun getProductCategory(){
        ProductCategoryPresenter(this).getAllProductCategory()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
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
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        when (view!!.id) {
            R.id.category_menu_item_container -> {
                val cm = adapterMenu!!.getItem(position)
                adapterMainMenu!!.replaceItems(sliderMainMenuList)
            }
        }
    }

    override fun onSuccessGetProductCategory(dataProductCat: ArrayList<DataProductCategory>?) {

        //Title Menu
        categoryMenuList = dataProductCat as ArrayList<DataProductCategory>

        //Bind Item to Adapter
        adapterMenu = CategoryMenuAdapter(this, categoryMenuList, this)
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
    }

    override fun onErrorGetProductCategory(msg: String) {
        Toast.makeText(context,"Failed to get data category", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {

        sliderMainMenuList = dataProductByCat as ArrayList<DataProduct>

        //Setup Recycler View Slider Main Menu
        adapterMainMenu = SliderMainMenuAdapter(context!!, sliderMainMenuList)
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
    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(context,"Failed to get data product", Toast.LENGTH_SHORT)
    }

    override fun passReloadItem(namaCategory: String) {
        ProductByCategoryPresenter(this).getAllProductByCategory(namaCategory)
    }
}
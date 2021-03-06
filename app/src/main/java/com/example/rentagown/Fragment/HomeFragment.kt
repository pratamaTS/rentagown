package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
import com.example.rentagown.Response.Notification.DataNotification
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.Response.ProductCategory.DataProductCategory
import com.example.rentagown.Response.Promo.DataPromo
import com.example.rentagown.v2.ui.choosedate.ChooseDateActivity
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), View.OnClickListener,
    ItemClickListener, ProductCategoryInterface, ProductByCategoryInterface, PromoInterface, FavoriteGownInterface, NewGownInterface, GetNotificationInterface, CategoryMenuAdapter.ReloadItemInterface {
    private lateinit var imWishlist: ImageButton
    private lateinit var imNotification: ImageView
    private lateinit var btnSeeAllCategory: Button
    private lateinit var btnSeeAllPromo: Button
    private lateinit var btnSeeAllFavoriteGown: Button
    private lateinit var btnSeeAllNewGown: Button
    private lateinit var rvTitleMenu: RecyclerView
    private lateinit var rvSliderMenu: RecyclerView
    private lateinit var rvSliderPromo: RecyclerView
    private lateinit var layoutPromoEmpty: ImageView
    private lateinit var searchView: SearchView
    private lateinit  var layoutPromo: ConstraintLayout
    private lateinit var layoutFavoriteGown: ConstraintLayout
    private lateinit var rvSliderFavoriteGown: RecyclerView
    private lateinit var rvSliderNewGown: RecyclerView
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
    var itemDecorSet: Boolean = false
    var tvNoItemHome: TextView? = null
    private lateinit var swipeRefreshHome: SwipeRefreshLayout
    private lateinit var pbProcat: IndeterminateCenteredRoundCornerProgressBar
    private lateinit var pbPromo: IndeterminateCenteredRoundCornerProgressBar
    private lateinit var pbFavGown: IndeterminateCenteredRoundCornerProgressBar
    private lateinit var pbNewGown: IndeterminateCenteredRoundCornerProgressBar
    var badgeNotif: View? = null
    var countNotif: Int = 0

    companion object {
        private const val COUNT_NOTIF = "count_notif"
        private const val STATUS_PAYMENT = "status_payment"
        private const val READ_NOTIF = "read_notif"
    }

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
        layoutFavoriteGown = view.findViewById(R.id.layout_favorite_gown)
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
        badgeNotif = view.findViewById(R.id.badge_notif)

        swipeRefreshHome.setOnRefreshListener {
            itemDecorSet = true
            getData()
            swipeRefreshHome.isRefreshing = false
        }

        getData()

        searchView.isEnabled = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //use this action
                val search = Intent(activity, SearchViewActivity::class.java)
                search.putExtra("product_name", query)
                startActivity(search)

                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        imWishlist.setOnClickListener(this@HomeFragment)
        imNotification.setOnClickListener(this@HomeFragment)
        btnSeeAllCategory.setOnClickListener(this@HomeFragment)
        btnSeeAllPromo.setOnClickListener(this@HomeFragment)
        btnSeeAllFavoriteGown.setOnClickListener(this@HomeFragment)
        btnSeeAllNewGown.setOnClickListener(this@HomeFragment)


        return view
    }

    private fun getData(){
        ProductCategoryPresenter(this).getAllProductCategory()
        PromoPresenter(this).getAllPromo()
        FavoriteGownPresenter(this).getAllFavoriteGown()
        NewGownPresenter(this).getAllNewGown()
        activity?.let { mAct ->
            if(mAct is MainAfterActivity) {
                GetNotificationPresenter(this).getAllNotification(requireContext())
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_wishlist -> {
                val wishlist = Intent(activity, WishlistActivity::class.java)
                startActivity(wishlist)
            }
            R.id.im_notification -> {
                Intent(activity, NotificationActivity::class.java).apply {
                    startActivityForResult(this, NotificationActivity.REQ_READ_NOTIFICATION)
                }
            }
            R.id.btn_see_all_category -> {
                val categoryProduct = Intent(activity, ProductGownActivity::class.java)
                startActivity(categoryProduct)
            }
            R.id.btn_see_all_promo -> {
                val promo = Intent(activity, PromoActivity::class.java)
                startActivity(promo)
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
        if (view != null) {
            when (view.id) {
                R.id.category_menu_item_container -> {
                    val cm = adapterMenu!!.getItem(position)
                    adapterMainMenu!!.replaceItems(sliderMainMenuList!!)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == NotificationActivity.REQ_READ_NOTIFICATION) {
            setBadgeNotif(true)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSuccessGetProductCategory(dataProductCat: ArrayList<DataProductCategory>?) {

        //Title Menu
        categoryMenuList = dataProductCat

        if(categoryMenuList?.isNotEmpty() == true) {
            //Bind Item to Adapter
            adapterMenu = CategoryMenuAdapter(this, categoryMenuList!!, this)
            rvTitleMenu.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvTitleMenu.setAdapter(adapterMenu)

            if (itemDecorSet == false) {
                rvTitleMenu.addItemDecoration(ItemDecorationSlider(16))
            }
            adapterMenu?.selectCategory(0)

            // preselect first category
            selectedCategoryMenu = adapterMenu!!.getItem(0)


            //Slider Main Menu
            ProductByCategoryPresenter(this).getAllProductByCategory(selectedCategoryMenu!!.nameProductCategory.toString())
        } else{
            Toast.makeText(context, "There is no category data", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onErrorGetProductCategory(msg: String) {
        Toast.makeText(context, "Failed to get data category", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {

        pbProcat.visibility = View.GONE
        sliderMainMenuList = dataProductByCat
        rvSliderMenu.visibility = View.VISIBLE

        //Setup Recycler View Slider Main Menu
        if(dataProductByCat?.isNotEmpty() == true) {
            rvSliderMenu.visibility = View.VISIBLE
            adapterMainMenu = sliderMainMenuList?.let { SliderMainMenuAdapter(it) }
            rvSliderMenu.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvSliderMenu.setAdapter(adapterMainMenu!!)
            if (itemDecorSet == false) {
                rvSliderMenu.addItemDecoration(ItemDecorationSlider(16))
                Log.d("decor padding", "true")
            }
        }else{
            rvSliderMenu.visibility = View.GONE
        }
    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(context, "Failed to get data product", Toast.LENGTH_SHORT).show()
    }

    override fun passReloadItem(namaCategory: String) {
        itemDecorSet = true
        ProductByCategoryPresenter(this).getAllProductByCategory(namaCategory)
    }

    override fun onSuccessGetNewGown(dataNewGown: ArrayList<DataNewGown>?) {
        //Slider New Gown
        pbNewGown.visibility = View.GONE
        rvSliderNewGown.visibility = View.VISIBLE

        //Setup Recycler View New Gown
        adapterNewGown = SliderNewGownAdapter(dataNewGown ?: arrayListOf())
        rvSliderNewGown.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvSliderNewGown.setAdapter(adapterNewGown)
    }

    override fun onErrorGetNewGown(msg: String) {
        Toast.makeText(context, "Failed to get data new gown", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetFavoriteGown(dataFavoriteGown: ArrayList<DataFavoriteGown?>?) {
        //Slider Favorite Gown
        pbFavGown.visibility = View.GONE
        if(dataFavoriteGown?.isNotEmpty() == true){
            favoriteGownList = dataFavoriteGown as ArrayList<DataFavoriteGown>
            rvSliderFavoriteGown.visibility = View.VISIBLE


            //Setup Recycler View Favorite Gown
            adapterFavoriteGown = SliderFavoriteGownAdapter(favoriteGownList ?: arrayListOf())
            rvSliderFavoriteGown.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            rvSliderFavoriteGown.setAdapter(adapterFavoriteGown)
        } else {
            layoutFavoriteGown.visibility = View.GONE
        }

    }

    override fun onErrorGetFavoriteGown(msg: String) {
        Toast.makeText(context, "Failed to get data favorite gown", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetPromo(dataPromo: ArrayList<DataPromo>?) {
        pbPromo.visibility = View.GONE

        promoList = dataPromo ?: arrayListOf()

        if(promoList.isNotEmpty()){

            layoutPromoEmpty.visibility = View.GONE
            rvSliderPromo.visibility = View.VISIBLE

            //Setup Recycler View Promo
            adapterPromo = SliderPromoAdapter(promoList ?: arrayListOf())
            rvSliderPromo.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )

            rvSliderPromo.setAdapter(adapterPromo)
        }else {
            layoutPromoEmpty.setVisibility(View.VISIBLE)
            layoutPromo.setVisibility(View.GONE)
        }
    }

    override fun onErrorGetPromo(msg: String) {
        Toast.makeText(context, "Failed to get data promo", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetNotification(dataNotification: ArrayList<DataNotification>?) {
        Log.d("data notif", dataNotification?.size.toString())
        Log.d("data notif isi", dataNotification.toString())
        if(context != null) {
            var prefs: SharedPreferences = requireContext().getSharedPreferences(
                requireContext().getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            val oldNotifCount: Int = prefs.getInt(COUNT_NOTIF, 0)
            var statusRead: Boolean = prefs.getBoolean(READ_NOTIF, false)
            var statusConfirmPayment: Boolean = prefs.getBoolean(STATUS_PAYMENT, false)
            var dumpStatus: Array<Int>? = null

            if (dataNotification.isNullOrEmpty()) {
                countNotif = 0
                editor.putInt(COUNT_NOTIF, countNotif)
                editor.commit()
            } else {
                countNotif = dataNotification.size

//            Log.d("count notif", countNotif.toString())
//            Log.d("old count notif", oldNotifCount.toString())
//            Log.d("status notif", statusRead.toString())
//            Log.d("dump status notif", dumpStatus.toString())

                if (countNotif > oldNotifCount) {
                    editor.putInt(COUNT_NOTIF, countNotif)
                    editor.putBoolean(READ_NOTIF, false)
                    editor.commit()

                    setBadgeNotif(false)
                } else {
                    if (statusRead == true) {
                        setBadgeNotif(true)
                    } else {
                        setBadgeNotif(false)
                    }
                }
            }
        }
    }

    override fun onErrorGetNotification(msg: String) {
        if(context != null) {
            Toast.makeText(requireContext(), "Failed to get notification", Toast.LENGTH_SHORT).show()
        }
    }

    fun setBadgeNotif(readNotif: Boolean){
        var prefs: SharedPreferences = requireContext().getSharedPreferences(
            requireContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()

        editor.putBoolean(READ_NOTIF, true)
        editor.commit()

        when(readNotif) {
            false -> badgeNotif?.visibility = View.VISIBLE
            true -> badgeNotif?.visibility = View.GONE
        }

    }
}
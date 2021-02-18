package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.DateBookingActivity
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.SeeNoAvailableDateActivity
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Adapter.SimilarCategoryAdapter
import com.example.rentagown.Body.WishlistBody
import com.example.rentagown.Connection.Interface.AddWishlistInterface
import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Presenter.AddAddressPresenter
import com.example.rentagown.Connection.Presenter.AddWishlistPresenter
import com.example.rentagown.Connection.Presenter.ProductByCategoryPresenter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.R
import com.example.rentagown.Response.CreateWishlist.DataAddWishlist
import com.example.rentagown.Response.Product.DataProduct
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class OverviewFragment : Fragment(), View.OnClickListener, ProductByCategoryInterface, AddWishlistInterface {
    var rvSimilarCategory: RecyclerView? = null
    var tvProductName: TextView? = null
    var tvProductPrice: TextView? = null
    var tvProductFinalPrice: TextView? = null
    var tvDiscount: TextView? = null
    var tvStartDate: TextView? = null
    var tvEndDate: TextView? = null
    var switchServices: SwitchCompat? = null
    var productList: ArrayList<DataProduct> = ArrayList()
    var similarCategoryAdapter: SimilarCategoryAdapter? = null
    var btnStartDate: LinearLayout? = null
    var btnLike: ImageButton? = null
    var btnSeeNoAvailableDate: Button? = null
    var idProduct: String? = null
    var productCategory: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var productFinalPrice: Int? = null
    var productPromoAmount: Int? = null
    var productQuantity: Int? = null
    var services: Int? = null
    var startDate: String? = null
    var endDate: String? = null
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    var token: String? = null
    var loginFragment: LoginFragment = LoginFragment()
    var supportFragmentManager: FragmentManager? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_overview, container, false)

        val sessionManager = SessionManager(context!!)

        //INIT VIEW
        rvSimilarCategory = view.findViewById(R.id.rv_similar_category)
        tvProductName = view.findViewById(R.id.tv_name_dress_detail_product)
        tvProductFinalPrice = view.findViewById(R.id.tv_price_detail_product)
        tvProductPrice = view.findViewById(R.id.tv_price_old_dress_detail_product)
        tvDiscount = view.findViewById(R.id.tv_discount_detail_product)
        tvStartDate = view.findViewById(R.id.tv_start_date_vp)
        tvEndDate = view.findViewById(R.id.tv_end_date_vp)
        btnLike = view.findViewById(R.id.btn_like)
        switchServices = view.findViewById(R.id.toogle_switch_special_treatment)
        btnStartDate = view.findViewById(R.id.layout_detail_start_date)
        btnSeeNoAvailableDate = view.findViewById(R.id.btn_see_no_available_date)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        //Get Value
        idProduct = arguments?.getString("id_product")
        productCategory = arguments?.getString("name_product_category")
        productName = arguments?.getString("product_name")
        productPrice = arguments?.getInt("product_price")
        productFinalPrice = arguments?.getInt("final_price")
        productPromoAmount = arguments?.getInt("promo_amount")
        productQuantity = arguments?.getInt("product_quantity")
        startDate = arguments?.getString("start_date")
        endDate = arguments?.getString("end_date")

        //Set Value
        tvProductName!!.text = productName?.capitalize()?.trimEnd()
        if(startDate != "null" && endDate != "null"){
            tvStartDate!!.text = startDate
            tvEndDate!!.text = endDate
        }

        switchServices!!.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            when(isChecked){
                true -> {
                    services = 1
                    (activity as ViewProductActivity?)!!.setServices(services!!)
                }
                false -> {
                    services = 0
                    (activity as ViewProductActivity?)!!.setServices(services!!)
                }
            }
        })

        when(productPromoAmount) {
            0 -> {
                tvProductFinalPrice!!.setText(numberFormat.format(productFinalPrice))
                tvDiscount!!.visibility = View.INVISIBLE
                tvProductPrice!!.visibility = View.INVISIBLE
            }
            else -> {
                tvProductFinalPrice!!.setText(numberFormat.format(productFinalPrice))
                tvDiscount!!.setText(productPromoAmount.toString() + "%")
                tvProductPrice!!.setText(numberFormat.format(productPrice))
            }
        }

        //SET LISTENER
        btnStartDate?.setOnClickListener(this@OverviewFragment)
        btnLike?.setOnClickListener(this@OverviewFragment)
        btnSeeNoAvailableDate?.setOnClickListener(this@OverviewFragment)

        getProductSimilar()

        return view
    }

    private fun getProductSimilar(){
        ProductByCategoryPresenter(this).getAllProductByCategory(productCategory.toString())
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_detail_start_date -> {
                if (token != null) {
                    val date = Intent(activity, DateBookingActivity::class.java)

                    date.putExtra("id_product", idProduct)
                    date.putExtra("name_product_category", productCategory)
                    date.putExtra("product_name", productName)
                    date.putExtra("product_price", productPrice)
                    date.putExtra("final_price", productFinalPrice)
                    date.putExtra("promo_amount", productPromoAmount)
                    date.putExtra("product_quantity", productQuantity)

                    startActivity(date)
                    activity?.finish()
                } else {
                    val mainActivity = Intent(activity, MainActivity::class.java)
                    mainActivity.putExtra("login_check", true)
                    startActivity(mainActivity)
                }
            }
            R.id.btn_like -> {
                if (v === btnLike) {
                    if (token != null) {
                        AddWishlistPresenter(this).addWishlist(context!!, WishlistBody(idProduct))
                    } else {
                        val mainActivity = Intent(activity, MainActivity::class.java)
                        mainActivity.putExtra("login_check", true)
                        startActivity(mainActivity)
                    }
                }
            }
            R.id.btn_see_no_available_date -> {
                val noAvailbleDate = Intent(activity, SeeNoAvailableDateActivity::class.java)
                startActivity(noAvailbleDate)
            }
        }
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {
        productList = dataProductByCat as ArrayList<DataProduct>

        //Setup Recycler View
        similarCategoryAdapter = SimilarCategoryAdapter(context!!, productList)
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvSimilarCategory!!.setLayoutManager(gridLayoutManager)
        rvSimilarCategory!!.setAdapter(similarCategoryAdapter)

    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(context, "Failed to get data similar product", Toast.LENGTH_SHORT)
    }

    override fun onSuccessAddWishlist(dataAddWishlist: DataAddWishlist) {
        btnLike!!.setBackgroundResource(R.drawable.ic_like_gold_filled)
    }

    override fun onErrorAddWishlist(msg: String) {
        Toast.makeText(context, "Failed to add wishlist", Toast.LENGTH_SHORT)
    }
}
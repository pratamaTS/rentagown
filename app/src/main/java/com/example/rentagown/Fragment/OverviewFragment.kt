package com.example.rentagown.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.DateBookingActivity
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.SeeNoAvailableDateActivity
import com.example.rentagown.Adapter.SimilarCategoryAdapter
import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Presenter.ProductByCategoryPresenter
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class OverviewFragment : Fragment(), View.OnClickListener, ProductByCategoryInterface {
    var rvSimilarCategory: RecyclerView? = null
    var tvProductName: TextView? = null
    var tvProductPrice: TextView? = null
    var tvProductFinalPrice: TextView? = null
    var tvDiscount: TextView? = null
    var productList: ArrayList<DataProduct> = ArrayList()
    var similarCategoryAdapter: SimilarCategoryAdapter? = null
    var btnStartDate: LinearLayout? = null
    var btnEndDate:LinearLayout? = null
    var btnLike: ImageButton? = null
    var btnSeeNoAvailableDate: Button? = null
    var idProduct: String? = null
    var productCategory: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var productFinalPrice: Int? = null
    var productPromoAmount: Int? = null
    var productQuantity: Int? = null
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

        //INIT VIEW
        rvSimilarCategory = view.findViewById(R.id.rv_similar_category)
        tvProductName = view.findViewById(R.id.tv_name_dress_detail_product)
        tvProductFinalPrice = view.findViewById(R.id.tv_price_detail_product)
        tvProductPrice = view.findViewById(R.id.tv_price_old_dress_detail_product)
        tvDiscount = view.findViewById(R.id.tv_discount_detail_product)
        btnLike = view.findViewById(R.id.btn_like)
        btnStartDate = view.findViewById(R.id.layout_detail_start_date)
        btnEndDate = view.findViewById(R.id.layout_detail_end_date)
        btnSeeNoAvailableDate = view.findViewById(R.id.btn_see_no_available_date)

        //Get Value
        idProduct = arguments?.getString("id_product")
        productCategory = arguments?.getString("name_product_category")
        productName = arguments?.getString("product_name")
        productPrice = arguments?.getInt("product_price")
        productFinalPrice = arguments?.getInt("final_price")
        productPromoAmount = arguments?.getInt("promo_amount")
        productQuantity = arguments?.getInt("product_quantity")

        //Set Value
        tvProductName!!.text = productName?.capitalize()?.trimEnd()

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
        btnEndDate?.setOnClickListener(this@OverviewFragment)
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

            }
            R.id.layout_detail_end_date -> {
                val endDate = Intent(activity, DateBookingActivity::class.java)
                startActivity(endDate)
            }
            R.id.btn_like -> {
                if (v === btnLike) {
                    if(token != null) {
                        btnLike!!.setBackgroundResource(R.drawable.btn_like_selected)
                    }else{
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
}
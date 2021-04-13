package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.CategoryProductAdapter
import com.example.rentagown.Adapter.ProductAdapter
import com.example.rentagown.Connection.Interface.ProductAllInterface
import com.example.rentagown.Connection.Interface.ProductByCategoryInterface
import com.example.rentagown.Connection.Interface.ProductByPromoInterface
import com.example.rentagown.Connection.Interface.ProductCategoryInterface
import com.example.rentagown.Connection.Presenter.ProductAllPresenter
import com.example.rentagown.Connection.Presenter.ProductByCategoryPresenter
import com.example.rentagown.Connection.Presenter.ProductByPromoPresenter
import com.example.rentagown.Connection.Presenter.ProductCategoryPresenter
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.CategoryMenu
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import com.example.rentagown.Response.ProductCategory.DataProductCategory
import java.util.*
import kotlin.collections.ArrayList

class ProductGownActivity : AppCompatActivity(), View.OnClickListener,
    ItemClickListener, ProductAllInterface, ProductByCategoryInterface, ProductByPromoInterface, ProductCategoryInterface, CategoryProductAdapter.ReloadItemInterface {
    var back: ImageButton? = null
    var filter: ImageButton? = null
    var rvMenuCategory: RecyclerView? = null
    var rvProduct: RecyclerView? = null
    var categoryProductAdapter: CategoryProductAdapter? = null
    var categoryMenuList: ArrayList<CategoryMenu> = ArrayList()
    var productList: ArrayList<DataProduct> = ArrayList()
    var productAdapter: ProductAdapter? = null
    var checkPromo: Boolean = false

    private var selectedCategoryMenu: CategoryMenu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        filter = findViewById(R.id.im_filter)
        rvMenuCategory = findViewById(R.id.rv_menu_category_product)
        rvProduct = findViewById(R.id.rv_product)

        if(intent.hasExtra("check_promo")){
            checkPromo = intent.getBooleanExtra("check_promo", false)
            when(checkPromo){
                true -> {
                    rvMenuCategory?.visibility = View.INVISIBLE
                    getProductPromo()
                }
            }
        }else{
            getCategory()
        }

        //SET LISTENER
        back?.setOnClickListener(this)
        filter?.setOnClickListener(this)
    }

    private fun getCategory(){
        ProductCategoryPresenter(this).getAllProductCategory()
    }

    private fun getProductPromo() {
        ProductByPromoPresenter(this).getAllProductByPromo()
    }

    private fun getAllProduct() {
        ProductAllPresenter(this).getAllProduct()
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.im_filter -> {
                val filter = Intent(this@ProductGownActivity, FilterActivity::class.java)
                startActivity(filter)
            }
        }
    }

    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        when (view!!.id) {
            R.id.category_menu_item_container -> {
                val cm = categoryProductAdapter!!.getItem(position)
            }
        }
    }

    override fun onSuccessGetAllProduct(dataProduct: ArrayList<DataProduct>?) {
        if(dataProduct?.isNotEmpty() == true) {
            rvProduct?.visibility = View.VISIBLE
            productList = dataProduct as ArrayList<DataProduct>
            //Setup Recycler View Product
            productAdapter = ProductAdapter(this, productList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvProduct?.setLayoutManager(gridLayoutManager)
            rvProduct?.setAdapter(productAdapter)
        }else{
            rvProduct?.visibility = View.INVISIBLE
            Toast.makeText(this, "There is no item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onErrorGetAllProduct(msg: String) {
        Toast.makeText(this, "Failed to get data product", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {
        if(dataProductByCat?.isNotEmpty() == true) {
            rvProduct?.visibility = View.VISIBLE
            productList = dataProductByCat as ArrayList<DataProduct>
            //Setup Recycler View Product
            productAdapter = ProductAdapter(this, productList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvProduct?.setLayoutManager(gridLayoutManager)
            rvProduct?.setAdapter(productAdapter)
        } else{
            rvProduct?.visibility = View.INVISIBLE
            Toast.makeText(this, "There is no item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun passReloadItem(namaCategory: String) {
        if(namaCategory == "all"){
            getAllProduct()
        }else{
            getProductByCategory(namaCategory)
        }
    }

    private fun getProductByCategory(namaCategory: String){
        ProductByCategoryPresenter(this).getAllProductByCategory(namaCategory)
    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(this, "Failed to get data product by category", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductByPromo(dataProductByCat: ArrayList<DataProduct>?) {
        if(dataProductByCat?.isNotEmpty() == true) {
            rvProduct?.visibility = View.VISIBLE
            productList = dataProductByCat as ArrayList<DataProduct>
            //Setup Recycler View Product
            productAdapter = ProductAdapter(this, productList)
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvProduct?.setLayoutManager(gridLayoutManager)
            rvProduct?.setAdapter(productAdapter)
        }else{
            rvProduct?.visibility = View.INVISIBLE
            Toast.makeText(this, "There is no item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onErrorGetProductByPromo(msg: String) {
        Toast.makeText(this, "Failed to get data product by category", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductCategory(dataProductCat: ArrayList<DataProductCategory>?) {
        categoryMenuList.add(CategoryMenu(0, "All"))
        var i = 1
        if (dataProductCat != null) {
            for(data in dataProductCat){
                categoryMenuList.add(CategoryMenu(i, data.nameProductCategory?.capitalize().toString()))
                i = i + 1
            }
        }

        //Setup Recycler View Title Menu
        categoryProductAdapter = CategoryProductAdapter(this, categoryMenuList, this)
        rvMenuCategory?.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvMenuCategory?.setAdapter(categoryProductAdapter)
        rvMenuCategory?.addItemDecoration(ItemDecorationSlider(16))

        // preselect first category
        selectedCategoryMenu = categoryProductAdapter!!.getItem(0)
    }

    override fun onErrorGetProductCategory(msg: String) {
        Toast.makeText(this, "Failed to get data category", Toast.LENGTH_SHORT).show()
    }
}
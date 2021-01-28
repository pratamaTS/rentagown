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
import com.example.rentagown.Connection.Presenter.ProductAllPresenter
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.CategoryMenu
import com.example.rentagown.Model.Product
import com.example.rentagown.R
import com.example.rentagown.Response.Product.DataProduct
import java.util.*
import kotlin.collections.ArrayList

class ProductGownActivity : AppCompatActivity(), View.OnClickListener,
    ItemClickListener, ProductAllInterface, ProductByCategoryInterface {
    var back: ImageButton? = null
    var filter: ImageButton? = null
    var rvMenuCategory: RecyclerView? = null
    var rvProduct: RecyclerView? = null
    var categoryProductAdapter: CategoryProductAdapter? = null
    var categoryMenuList: ArrayList<CategoryMenu> = ArrayList()
    var productList: ArrayList<DataProduct> = ArrayList()
    var productAdapter: ProductAdapter? = null
    private var selectedCategoryMenu: CategoryMenu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_gown)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        filter = findViewById(R.id.im_filter)
        rvMenuCategory = findViewById(R.id.rv_menu_category_product)
        rvProduct = findViewById(R.id.rv_product)

        //Title Menu
        categoryMenuList = java.util.ArrayList()
        categoryMenuList?.add(CategoryMenu(0, "All"))
        categoryMenuList?.add(CategoryMenu(1, "Prewedding"))
        categoryMenuList?.add(CategoryMenu(2, "Wedding"))
        categoryMenuList?.add(CategoryMenu(3, "Family"))
        categoryMenuList?.add(CategoryMenu(4, "Maternity"))

        //Setup Recycler View Title Menu
        categoryProductAdapter = CategoryProductAdapter(categoryMenuList, this)
        rvMenuCategory?.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvMenuCategory?.setAdapter(categoryProductAdapter)
        rvMenuCategory?.addItemDecoration(ItemDecorationSlider(16))
        categoryProductAdapter!!.selectCategory(0)

        //Preselect first category
        selectedCategoryMenu = categoryProductAdapter!!.getItem(0)

        //Product
        getCategoryProduct(selectedCategoryMenu!!.idCategory)

        //SET LISTENER
        back?.setOnClickListener(this)
        filter?.setOnClickListener(this)
    }

    private fun getCategoryProduct(categoryId: Int) {
        when (categoryId) {
            0 -> {
                getAllProduct()
            }
            1 -> {
                getAllProductByCat("prewedding")
            }
            2 -> {
                getAllProductByCat("wedding")
            }
            3 -> {
                getAllProductByCat("family")
            }
            4 -> {
                getAllProductByCat("maternity")
            }
        }
    }

    private fun getAllProduct() {
        ProductAllPresenter(this).getAllProduct()
    }

    private fun getAllProductByCat(category: String) {

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
                productAdapter!!.replaceItems(productList)
            }
        }
    }

    override fun onSuccessGetAllProduct(dataProduct: ArrayList<DataProduct>?) {
        productList = dataProduct as ArrayList<DataProduct>

        //Setup Recycler View Product
        productAdapter = ProductAdapter(this, productList)
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvProduct?.setLayoutManager(gridLayoutManager)
        rvProduct?.setAdapter(productAdapter)
    }

    override fun onErrorGetAllProduct(msg: String) {
        Toast.makeText(this, "Failed to get data product", Toast.LENGTH_SHORT)
    }

    override fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?) {
        productList = dataProductByCat as ArrayList<DataProduct>

        //Setup Recycler View Product
        productAdapter = ProductAdapter(this, productList)
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvProduct?.setLayoutManager(gridLayoutManager)
        rvProduct?.setAdapter(productAdapter)
    }

    override fun onErrorGetProductByCategory(msg: String) {
        Toast.makeText(this, "Failed to get data product by category", Toast.LENGTH_SHORT)
    }
}
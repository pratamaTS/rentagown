package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.CategoryProductAdapter
import com.example.rentagown.Adapter.ProductAdapter
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.CategoryMenu
import com.example.rentagown.Model.Product
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class ProductGownActivity : AppCompatActivity(), View.OnClickListener,
    ItemClickListener {
    var back: ImageButton? = null
    var filter: ImageButton? = null
    var rvMenuCategory: RecyclerView? = null
    var rvProduct: RecyclerView? = null
    var categoryProductAdapter: CategoryProductAdapter? = null
    var categoryMenuList: ArrayList<CategoryMenu> = ArrayList()
    var productList: ArrayList<Product>? = null
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
        categoryMenuList.add(CategoryMenu(0, "All"))
        categoryMenuList.add(CategoryMenu(1, "Prewedding"))
        categoryMenuList.add(CategoryMenu(2, "Wedding"))
        categoryMenuList.add(CategoryMenu(3, "Family"))
        categoryMenuList.add(CategoryMenu(4, "Maternity"))

        //Setup Recycler View Title Menu
        categoryProductAdapter = CategoryProductAdapter(categoryMenuList, this)
        rvMenuCategory!!.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rvMenuCategory!!.setAdapter(categoryProductAdapter)
        rvMenuCategory!!.addItemDecoration(ItemDecorationSlider(16))
        categoryProductAdapter!!.selectCategory(0)

        //Preselect first category
        selectedCategoryMenu = categoryProductAdapter!!.getItem(0)

        //Product
        productList = getCategoryProduct(selectedCategoryMenu!!.idCategory)

        //Setup Recycler View Product
        productAdapter = ProductAdapter(this, productList!!)
        rvProduct!!.setLayoutManager(GridLayoutManager(this, 2))
        rvProduct!!.setAdapter(productAdapter)

        //SET LISTENER
        back!!.setOnClickListener(this)
        filter!!.setOnClickListener(this)
    }

    private fun getCategoryProduct(categoryId: Int): ArrayList<Product>? {
        val dummyProduct = ArrayList<Product>()
        when (categoryId) {
            0 -> {
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        0,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
            }
            1 -> {
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        1,
                        R.drawable.prewedding_1,
                        "Selina Colourblock Camisole Dress",
                        "Rp. 5.000.000"
                    )
                )
            }
            2 -> {
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        2,
                        R.drawable.wedding_1,
                        "Pearla Tiered Maxi Dress",
                        "Rp. 6.000.000"
                    )
                )
            }
            3 -> {
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        3,
                        R.drawable.family_1,
                        "Family of Ceminata Gown",
                        "Rp. 10.000.000"
                    )
                )
            }
            4 -> {
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
                dummyProduct.add(
                    Product(
                        4,
                        R.drawable.maternity_1,
                        "Blue Ocean elegant maternity gown",
                        "Rp. 2.200.000"
                    )
                )
            }
        }
        return dummyProduct
    }

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
                productAdapter!!.replaceItems(getCategoryProduct(cm.idCategory))
            }
        }
    }
}
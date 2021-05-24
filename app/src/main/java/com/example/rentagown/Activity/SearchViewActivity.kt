package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ProductSearchAdapter
import com.example.rentagown.Adapter.SliderMainMenuAdapter
import com.example.rentagown.Adapter.SliderPromoAdapter
import com.example.rentagown.Connection.Interface.SearchInterface
import com.example.rentagown.Connection.Presenter.SearchPresenter
import com.example.rentagown.Decoration.ItemDecorationSlider
import com.example.rentagown.R
import com.example.rentagown.Response.Search.DataSearch

class SearchViewActivity : AppCompatActivity(), SearchInterface {
    private lateinit var rvSearchView: RecyclerView
    private lateinit var tvNoItemFound: TextView
    private lateinit var searchViewProduct: androidx.appcompat.widget.SearchView
    var productName: String? = null
    var adapterProductSearch: ProductSearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        rvSearchView = findViewById(R.id.rv_list_search_product)
        searchViewProduct = findViewById(R.id.search_view_product)
        tvNoItemFound = findViewById(R.id.tv_no_item_found)

        if(intent.hasExtra("product_name")){
            productName = intent.getStringExtra("product_name")
            search(productName.toString())
        }

        searchViewProduct.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //use this action
                search(query)

                searchViewProduct.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun search(productName: String) {
        SearchPresenter(this).search(productName)
    }

    override fun onSuccessGetProductBySearch(dataProductBySearch: ArrayList<DataSearch>?) {
        if(dataProductBySearch.isNullOrEmpty()){
            rvSearchView.visibility = View.GONE
            tvNoItemFound.visibility = View.VISIBLE
        }else {
            tvNoItemFound.visibility = View.GONE
            rvSearchView.visibility = View.VISIBLE

            adapterProductSearch = dataProductBySearch.let { ProductSearchAdapter(this, it) }
            val gridLayoutManager = GridLayoutManager(this, 2)
            rvSearchView.setLayoutManager(gridLayoutManager)
            rvSearchView.setAdapter(adapterProductSearch)
        }
    }

    override fun onErrorGetProductBySearch(msg: String?) {
        Toast.makeText(this, "Failed to get data", Toast.LENGTH_SHORT).show()
    }
}
package com.example.rentagown.Activity

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class SearchViewActivity : AppCompatActivity() {
    private lateinit var rvSearchView: RecyclerView
    private lateinit var searchViewProduct: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        rvSearchView = findViewById(R.id.rv_list_search_product)
        searchViewProduct = findViewById(R.id.search_view_product)
    }
}
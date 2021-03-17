package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Search.DataSearch


interface SearchInterface {
    fun onSuccessGetProductBySearch(dataProductBySearch: ArrayList<DataSearch>?)
    fun onErrorGetProductBySearch(msg:String?)
}
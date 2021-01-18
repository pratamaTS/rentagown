package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.ProductCategory.DataProductCategory

interface ProductCategoryInterface {
    fun onSuccessGetProductCategory(dataProductCat: ArrayList<DataProductCategory>?)
    fun onErrorGetProductCategory(msg: String)
}
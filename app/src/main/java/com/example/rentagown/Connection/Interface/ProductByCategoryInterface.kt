package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Product.DataProduct

interface ProductByCategoryInterface {
    fun onSuccessGetProductByCategory(dataProductByCat: ArrayList<DataProduct>?)
    fun onErrorGetProductByCategory(msg: String)
}
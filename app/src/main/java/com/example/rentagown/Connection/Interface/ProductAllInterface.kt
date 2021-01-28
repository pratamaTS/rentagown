package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Product.DataProduct

interface ProductAllInterface {
    fun onSuccessGetAllProduct(dataProduct: ArrayList<DataProduct>?)
    fun onErrorGetAllProduct(msg: String)
}
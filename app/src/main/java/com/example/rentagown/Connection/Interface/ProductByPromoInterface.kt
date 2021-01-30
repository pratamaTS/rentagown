package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Product.DataProduct

interface ProductByPromoInterface {
    fun onSuccessGetProductByPromo(dataProductByCat: ArrayList<DataProduct>?)
    fun onErrorGetProductByPromo(msg: String)
}
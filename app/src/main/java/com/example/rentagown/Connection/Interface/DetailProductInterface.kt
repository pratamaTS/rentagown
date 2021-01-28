package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Product.DataDetailProduct

interface DetailProductInterface {
    fun onSuccessGetDetailProduct(dataDetailProduct: DataDetailProduct?)
    fun onErrorGetDetailProduct(msg: String)
}
package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.GetWishlist.DataWishlist

interface GetWishlistInterface {
    fun onSuccessGetWishlist(dataWishlist: ArrayList<DataWishlist>?)
    fun onErrorGetWishlist(msg: String)
}
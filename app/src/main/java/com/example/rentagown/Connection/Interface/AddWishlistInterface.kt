package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.CreateAddress.DataAddAddress
import com.example.rentagown.Response.CreateWishlist.DataAddWishlist

interface AddWishlistInterface {
    fun onSuccessAddWishlist(dataAddWishlist: DataAddWishlist)
    fun onErrorAddWishlist(msg: String)
}
package com.example.rentagown.Response.CreateWishlist

import com.google.gson.annotations.SerializedName

class DataAddWishlist: ResponseCreateWishlist() {

    @field:SerializedName("id_product")
    val idProduct: String? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("id_user")
    val idUser: String? = null

    @field:SerializedName("is_wishlist")
    val isWishlist: Int? = null

    @field:SerializedName("id_wishlist")
    val idWishlist: String? = null
}

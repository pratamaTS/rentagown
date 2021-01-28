package com.example.rentagown.Response.Product

import com.google.gson.annotations.SerializedName

class DataPhoto: DataDetailProduct() {

    @field:SerializedName("id_photo")
    val idPhoto: String? = null

    @field:SerializedName("path_photo")
    val pathPhoto: String? = null
}
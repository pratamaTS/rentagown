package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class Gown(
    @SerializedName("id") val id: String?,
    @SerializedName("id_product") val productId: String?,
) : BaseModel()
package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

open class BaseModel {

    @SerializedName("created_at")
    val createdAt: String? = null

    @SerializedName("updated_at")
    val updatedAt: String? = null

    @SerializedName("deleted_at")
    val deletedAt: String? = null

}
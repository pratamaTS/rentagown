package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class BasePagingResp<T> (

    @SerializedName("ok")
    val ok: Boolean? = false,

    @SerializedName("status")
    val status: String? = "",

    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("data")
    val data: List<T>? = listOf(),

    @SerializedName("size")
    val size: Int? = 0,

    @SerializedName("page")
    val page: Int? = 0,

    @SerializedName("total")
    val total: Int? = 0

)
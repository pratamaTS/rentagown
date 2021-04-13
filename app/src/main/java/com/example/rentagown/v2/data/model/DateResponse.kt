package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class DateResponse (

    @SerializedName("ok")
    val ok: Boolean? = false,

    @SerializedName("status")
    val status: String? = "",

    @SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("error")
    val error: String? = null
)
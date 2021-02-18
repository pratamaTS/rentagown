package com.example.rentagown.Response.CheckDate

import com.google.gson.annotations.SerializedName

class DataCheckDate: ResponseCheckDate() {

    @field:SerializedName("is_available")
    val isAvailable: Boolean? = null
}
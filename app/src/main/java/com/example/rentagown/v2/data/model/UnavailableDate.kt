package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class UnavailableDate (

    @SerializedName("start_date")
    val startDate: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

)
package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class Fitting (

    @SerializedName("id_transaction")
    val transactionId: String? = null,

    @SerializedName("id_fitting")
    val fittingId: String? = null,

    @SerializedName("bust")
    val bustSize: Int? = null,

    @SerializedName("arm_hole")
    val armHoleSize: Int? = null,

    @SerializedName("waist")
    val waistSize: Int? = null,

    @SerializedName("hip")
    val hipSize: Int? = null

)
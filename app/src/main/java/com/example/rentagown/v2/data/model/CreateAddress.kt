package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class CreateAddress(

    @SerializedName("address_label")
    val addressLabel: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("address_detail")
    val addressDetail: String,

    @SerializedName("name")
    val receiverName: String,

    @SerializedName("phone")
    val receiverPhoneNumber: String


)
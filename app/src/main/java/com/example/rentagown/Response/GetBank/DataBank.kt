package com.example.rentagown.Response.GetBank

import com.google.gson.annotations.SerializedName

class DataBank: ResponseGetBank() {

    @field:SerializedName("account_number")
    val accountNumber: String? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("account_name")
    val accountName: String? = null

    @field:SerializedName("bank_name")
    val bankName: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("id_bank")
    val idBank: String? = null

    @field:SerializedName("path_photo")
    val pathPhoto: String? = null
}
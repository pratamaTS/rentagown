package com.example.rentagown.Response.CreateAddress

import com.google.gson.annotations.SerializedName


class DataAddAddress: ResponseAddAddress() {

    @field:SerializedName("address_detail")
    val addressDetail: String? = null

    @field:SerializedName("id_address")
    val idAddress: String? = null

    @field:SerializedName("address")
    val address: String? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("phone")
    val phone: String? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("address_label")
    val addressLabel: String? = null

    @field:SerializedName("id_user")
    val idUser: String? = null
}
package com.example.rentagown.Response.EditProfile

import com.google.gson.annotations.SerializedName

class DataEditProfile: ResponseEditProfile() {

    @field:SerializedName("role")
    val role: String? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("phone")
    val phone: String? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("id_user")
    val idUser: String? = null

    @field:SerializedName("email")
    val email: String? = null

    @field:SerializedName("platform")
    val platform: String? = null

    @field:SerializedName("fcm_id")
    val fcmId: String? = null

    @field:SerializedName("path_photo")
    val pathPhoto: String? = null
}
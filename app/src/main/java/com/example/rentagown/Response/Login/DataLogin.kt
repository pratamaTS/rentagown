package com.example.rentagown.Response.Login

import com.google.gson.annotations.SerializedName

class DataLogin: ResponseLogin() {
    @field:SerializedName("access_token")
    val accessToken: String? = null

    @field:SerializedName("refresh_token")
    val refreshToken: String? = null
}
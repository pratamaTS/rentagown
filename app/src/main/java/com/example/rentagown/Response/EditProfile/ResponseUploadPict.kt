package com.example.rentagown.Response.EditProfile

import com.google.gson.annotations.SerializedName

open class ResponseUploadPict {
    @field:SerializedName("code")
    val code: Int? = null

    @field:SerializedName("data")
    val data: ArrayList<DataPict> = ArrayList()

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: String? = null
}
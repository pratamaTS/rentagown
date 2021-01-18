package com.example.rentagown.Response.Product

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseProduct {

    @field:SerializedName("data")
    val data: ArrayList<DataProduct> = ArrayList()

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: String? = null

    @field:SerializedName("code")
    val code: Int? = null

    @field:SerializedName("size")
    val size: Int? = null

    @field:SerializedName("page")
    val page: Int? = null

    @field:SerializedName("total")
    val total: Int? = null
}
package com.example.rentagown.Response.GetWishlist

import com.google.gson.annotations.SerializedName

open class ResponseGetWishlist {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataWishlist>? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

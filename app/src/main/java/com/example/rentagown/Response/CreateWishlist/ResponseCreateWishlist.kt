package com.example.rentagown.Response.CreateWishlist

import com.google.gson.annotations.SerializedName

open class ResponseCreateWishlist{

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataAddWishlist? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}
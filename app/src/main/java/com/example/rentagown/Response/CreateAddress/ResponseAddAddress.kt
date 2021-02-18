package com.example.rentagown.Response.CreateAddress

import com.google.gson.annotations.SerializedName

open class ResponseAddAddress {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataAddAddress? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

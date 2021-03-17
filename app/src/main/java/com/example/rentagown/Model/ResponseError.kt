package com.example.rentagown.Model

import com.google.gson.annotations.SerializedName

data class ResponseError(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

package com.example.rentagown.Response.ForgotPassword

import com.google.gson.annotations.SerializedName

data class ResponseForgotPassword(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null,

	@field:SerializedName("status")
	val status: String? = null
)

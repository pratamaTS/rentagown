package com.example.rentagown.Response.Login

import com.google.gson.annotations.SerializedName

open class ResponseLogin {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataLogin? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

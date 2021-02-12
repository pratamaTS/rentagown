package com.example.rentagown.Response.Register

import com.google.gson.annotations.SerializedName

open class ResponseRegister {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataRegister? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

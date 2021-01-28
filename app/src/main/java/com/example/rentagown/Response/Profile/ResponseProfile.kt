package com.example.rentagown.Response.Profile

import com.google.gson.annotations.SerializedName

open class ResponseProfile {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataProfile? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null
}

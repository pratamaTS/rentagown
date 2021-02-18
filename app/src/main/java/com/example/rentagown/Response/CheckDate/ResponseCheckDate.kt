package com.example.rentagown.Response.CheckDate

import com.google.gson.annotations.SerializedName

open class ResponseCheckDate {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataCheckDate? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

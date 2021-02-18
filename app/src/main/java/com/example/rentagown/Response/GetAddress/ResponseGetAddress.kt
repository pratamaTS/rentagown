package com.example.rentagown.Response.GetAddress

import com.google.gson.annotations.SerializedName

open class ResponseGetAddress {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataAddress>? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

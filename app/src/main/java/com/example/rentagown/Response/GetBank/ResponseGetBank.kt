package com.example.rentagown.Response.GetBank

import com.google.gson.annotations.SerializedName

open class ResponseGetBank {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataBank>? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

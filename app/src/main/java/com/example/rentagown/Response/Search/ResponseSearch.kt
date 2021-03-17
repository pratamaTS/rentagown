package com.example.rentagown.Response.Search

import com.google.gson.annotations.SerializedName

open class ResponseSearch {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataSearch>? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

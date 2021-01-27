package com.example.rentagown.Response.FavoriteGown

import com.google.gson.annotations.SerializedName

open class ResponseFavoriteGown {

	@field:SerializedName("total")
	val total: Int? = null

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataFavoriteGown?>? = null

	@field:SerializedName("size")
	val size: Int? = null

	@field:SerializedName("page")
	val page: Int? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

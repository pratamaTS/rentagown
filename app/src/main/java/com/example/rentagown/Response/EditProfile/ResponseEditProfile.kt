package com.example.rentagown.Response.EditProfile

import com.google.gson.annotations.SerializedName

open class ResponseEditProfile {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataEditProfile? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

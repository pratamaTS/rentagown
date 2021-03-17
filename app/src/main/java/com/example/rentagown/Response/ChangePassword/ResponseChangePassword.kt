package com.example.rentagown.Response.ChangePassword

import com.google.gson.annotations.SerializedName

open class ResponseChangePassword {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataChangePassword? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

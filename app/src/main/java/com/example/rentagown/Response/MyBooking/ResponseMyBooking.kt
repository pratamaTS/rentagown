package com.example.rentagown.Response.MyBooking

import com.google.gson.annotations.SerializedName

open class ResponseMyBooking {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataMyBooking? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null
}

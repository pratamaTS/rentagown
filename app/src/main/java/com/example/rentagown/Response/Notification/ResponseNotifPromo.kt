package com.example.rentagown.Response.Notification

import com.google.gson.annotations.SerializedName

open class ResponseNotifPromo {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataNotifPromo>? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}



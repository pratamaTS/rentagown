package com.example.rentagown.Response.Notification

import com.google.gson.annotations.SerializedName

open class ResponseNotification {

	@field:SerializedName("total")
	val total: Int? = null

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: ArrayList<DataNotification>? = null

	@field:SerializedName("size")
	val size: Int? = null

	@field:SerializedName("page")
	val page: Int? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null
}
package com.example.rentagown.Response.Promo.PromoDetail

import com.google.gson.annotations.SerializedName

open class ResponsePromoDetail {

	@field:SerializedName("code")
	val code: Int? = null

	@field:SerializedName("data")
	val data: DataPromoDetail? = null

	@field:SerializedName("ok")
	val ok: Boolean? = null

	@field:SerializedName("status")
	val status: String? = null
}

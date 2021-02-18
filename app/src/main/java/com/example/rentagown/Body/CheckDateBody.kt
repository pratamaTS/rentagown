package com.example.rentagown.Body

import com.google.gson.annotations.SerializedName

data class CheckDateBody(

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("end_date")
	val endDate: String? = null
)

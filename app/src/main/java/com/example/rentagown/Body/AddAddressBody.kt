package com.example.rentagown.Body

import com.google.gson.annotations.SerializedName

data class AddAddressBody(

	@field:SerializedName("address_detail")
	val addressDetail: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("address_label")
	val addressLabel: String? = null
)

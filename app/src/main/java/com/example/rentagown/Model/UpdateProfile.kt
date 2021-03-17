package com.example.rentagown.Model

import com.google.gson.annotations.SerializedName

data class UpdateProfile(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("path_photo")
	val pathPhoto: String? = null
)

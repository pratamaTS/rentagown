package com.example.rentagown.Model

import com.google.gson.annotations.SerializedName

data class ChangePassword(

	@field:SerializedName("password")
	val password: String? = null
)

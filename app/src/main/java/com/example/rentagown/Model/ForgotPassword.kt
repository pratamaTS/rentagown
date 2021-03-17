package com.example.rentagown.Model

import com.google.gson.annotations.SerializedName

data class ForgotPassword(

	@field:SerializedName("email")
	val email: String? = null
)

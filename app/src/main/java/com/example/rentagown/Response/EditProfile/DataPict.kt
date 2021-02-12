package com.example.rentagown.Response.EditProfile

import com.google.gson.annotations.SerializedName

class DataPict: ResponseUploadPict() {

	@field:SerializedName("path_photo")
	val pathPhoto: String? = null
}

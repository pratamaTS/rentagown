package com.example.rentagown.Response.ProductCategory

import com.google.gson.annotations.SerializedName

data class DataProductCategory(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id_product_category")
	val idProductCategory: String? = null,

	@field:SerializedName("name_product_category")
	val nameProductCategory: String? = null
)

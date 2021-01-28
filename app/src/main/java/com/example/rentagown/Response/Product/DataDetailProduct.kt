package com.example.rentagown.Response.Product

import com.google.gson.annotations.SerializedName

open class DataDetailProduct: ResponseDetailProduct() {

	@field:SerializedName("promo_name")
	val promoName: String? = null

	@field:SerializedName("Rating")
	val rating: ArrayList<Any?>? = null

	@field:SerializedName("product_status")
	val productStatus: Int? = null

	@field:SerializedName("created_at")
	val createdAtDet: String? = null

	@field:SerializedName("id_product_category")
	val idProductCategory: String? = null

	@field:SerializedName("promo_code")
	val promoCode: String? = null

	@field:SerializedName("Photo")
	val photo: ArrayList<DataPhoto> = ArrayList()

	@field:SerializedName("product_price")
	val productPrice: Int? = null

	@field:SerializedName("id_user")
	val idUser: String? = null

	@field:SerializedName("product_name")
	val productName: String? = null

	@field:SerializedName("id_promo")
	val idPromo: String? = null

	@field:SerializedName("product_quantity")
	val productQuantity: Int? = null

	@field:SerializedName("is_wishlist")
	val isWishlist: Int? = null

	@field:SerializedName("product_desc")
	val productDesc: String? = null

	@field:SerializedName("promo_amount")
	val promoAmount: Int? = null

	@field:SerializedName("id_product")
	val idProductDet: String? = null

	@field:SerializedName("updated_at")
	val updatedAtDet: String? = null

	@field:SerializedName("final_price")
	val finalPrice: Int? = null

	@field:SerializedName("name")
	val name: String? = null

	@field:SerializedName("id")
	val id: String? = null

	@field:SerializedName("name_product_category")
	val nameProductCategory: String? = null

	@field:SerializedName("promo_exp")
	val promoExp: String? = null
}

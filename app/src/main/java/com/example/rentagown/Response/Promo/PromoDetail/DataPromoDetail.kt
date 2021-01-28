package com.example.rentagown.Response.Promo.PromoDetail

import com.google.gson.annotations.SerializedName

class DataPromoDetail: ResponsePromoDetail() {

    @field:SerializedName("promo_amount")
    val promoAmount: Int? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("terms_conditions")
    val termsConditions: String? = null

    @field:SerializedName("promo_name")
    val promoName: String? = null

    @field:SerializedName("promo_desc")
    val promoDesc: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("promo_code")
    val promoCode: String? = null

    @field:SerializedName("id_promo")
    val idPromo: String? = null

    @field:SerializedName("promo_exp")
    val promoExp: String? = null

    @field:SerializedName("promo_stock")
    val promoStock: Int? = null

    @field:SerializedName("path_photo")
    val pathPhoto: String? = null
}
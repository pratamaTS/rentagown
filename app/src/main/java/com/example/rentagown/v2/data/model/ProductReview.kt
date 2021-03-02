package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class ProductReview(

    @SerializedName("id_rating")
    val ratingId: String? = null,

    @SerializedName("id_transaction")
    val transactionId: String? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("id_user")
    val userId: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("rating_score")
    val reviewStar: Float? = 0f,

    @SerializedName("rating_desc")
    val reviewComment: String? = null,

    @SerializedName("review_date_time")
    val reviewDateTime: String? = null

) : BaseModel()
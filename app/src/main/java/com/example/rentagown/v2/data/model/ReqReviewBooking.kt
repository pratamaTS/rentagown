package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class ReqReviewBooking(

    @SerializedName("id_product")
    val productId: String?,

    @SerializedName("id_transaction")
    val transactionId: String?,

    @SerializedName("rating_score")
    val reviewStar: Int?,

    @SerializedName("rating_desc")
    val reviewComment: String?

)
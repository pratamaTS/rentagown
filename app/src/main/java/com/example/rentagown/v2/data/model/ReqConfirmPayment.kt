package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class ReqConfirmPayment (

    @SerializedName("payment_bank_name")
    val sourceBankName: String? = null,

    @SerializedName("payment_account_number")
    val sourceAccountNumber: String? = null,

    @SerializedName("payment_account_name")
    val sourceAccountName: String? = null,

    @SerializedName("payment_amount")
    val paymentAmount: Long? = null

)
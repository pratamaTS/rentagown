package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class ReqConfirm2ndPayment (

    @SerializedName("repayment_bank_name")
    val sourceBankName: String? = null,

    @SerializedName("repayment_account_number")
    val sourceAccountNumber: String? = null,

    @SerializedName("repayment_account_name")
    val sourceAccountName: String? = null,

    @SerializedName("repayment_amount")
    val paymentAmount: Long? = null

)
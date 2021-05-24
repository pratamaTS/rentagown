package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class ReqConfirmPayment (

    @SerializedName("invoice")
    val invoice: String? = null,

    @SerializedName("payment_source_bank_id")
    val idBank: String? = null,

    @SerializedName("other_payment_bank_name")
    val otherPaymentBankName: String? = null,

    @SerializedName("payment_source_acc_number")
    val sourceAccountNumber: String? = null,

    @SerializedName("payment_source_acc_name")
    val sourceAccountName: String? = null,

    @SerializedName("payment_amount")
    val paymentAmount: Long? = null

)
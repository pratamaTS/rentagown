package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Invoice(

    @field:SerializedName("payment_dest_bank_logo")
    val paymentDestBankLogo: String? = null,

    @field:SerializedName("payment_method_type")
    val paymentMethodType: Int? = null,

    @field:SerializedName("payment_status_name")
    val paymentStatusName: String? = null,

    @field:SerializedName("booking")
    val booking: Booking? = null,

    @field:SerializedName("notes")
    val notes: String? = null,

    @field:SerializedName("payment_source_acc_number")
    val paymentSourceAccNumber: String? = null,

    @field:SerializedName("payment_source_bank_logo")
    val paymentSourceBankLogo: String? = null,

    @field:SerializedName("payment_date_time")
    val paymentDateTime: String? = null,

    @field:SerializedName("id_transaction")
    val idTransaction: String? = null,

    @field:SerializedName("invoice_amount")
    val invoiceAmount: Long? = null,

    @field:SerializedName("payment_dest_bank_id")
    val paymentDestBankId: String? = null,

    @field:SerializedName("payment_source_bank_id")
    val paymentSourceBankId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("payment_dest_acc_number")
    val paymentDestAccNumber: String? = null,

    @field:SerializedName("payment_dest_bank_name")
    val paymentDestBankName: String? = null,

    @field:SerializedName("payment_source_acc_name")
    val paymentSourceAccName: String? = null,

    @field:SerializedName("payment_source_bank_name")
    val paymentSourceBankName: String? = null,

    @field:SerializedName("payment_status")
    val paymentStatus: Int? = null,

    @field:SerializedName("payment_amount")
    val paymentAmount: Long? = null,

    @field:SerializedName("id_user")
    val idUser: String? = null,

    @field:SerializedName("payment_method_name")
    val paymentMethodName: String? = null,

    @field:SerializedName("payment_deadline")
    val paymentDeadline: String? = null,

    @field:SerializedName("payment_dest_acc_name")
    val paymentDestAccName: String? = null,

    @field:SerializedName("invoice")
    val invoice: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("deleted_at")
    val deletedAt: String? = null,

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readParcelable(Booking::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(paymentDestBankLogo)
        parcel.writeValue(paymentMethodType)
        parcel.writeString(paymentStatusName)
        parcel.writeParcelable(booking, flags)
        parcel.writeString(notes)
        parcel.writeString(paymentSourceAccNumber)
        parcel.writeString(paymentSourceBankLogo)
        parcel.writeString(paymentDateTime)
        parcel.writeString(idTransaction)
        parcel.writeValue(invoiceAmount)
        parcel.writeString(paymentDestBankId)
        parcel.writeString(paymentSourceBankId)
        parcel.writeString(id)
        parcel.writeString(paymentDestAccNumber)
        parcel.writeString(paymentDestBankName)
        parcel.writeString(paymentSourceAccName)
        parcel.writeString(paymentSourceBankName)
        parcel.writeValue(paymentStatus)
        parcel.writeValue(paymentAmount)
        parcel.writeString(idUser)
        parcel.writeString(paymentMethodName)
        parcel.writeString(paymentDeadline)
        parcel.writeString(paymentDestAccName)
        parcel.writeString(invoice)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Invoice> {
        override fun createFromParcel(parcel: Parcel): Invoice {
            return Invoice(parcel)
        }

        override fun newArray(size: Int): Array<Invoice?> {
            return arrayOfNulls(size)
        }
    }

}
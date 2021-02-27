package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ReqCreateBooking (

    @SerializedName("paid_price")
    val paidPrice: Long? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("id_address")
    val addressId: String? = null,

    @SerializedName("id_bank")
    val bankId: String? = null,

    @SerializedName("phone")
    val phoneNumber: String? = null,

    @SerializedName("start_date")
    val startDate: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

    @SerializedName("down_payment")
    val downPayment: Long? = null,

    @SerializedName("payment_method")
    val paymentMethod: String? = null,

    @SerializedName("one_day_service")
    val oneDayService: Int? = null

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(paidPrice)
        parcel.writeString(productId)
        parcel.writeString(addressId)
        parcel.writeString(bankId)
        parcel.writeString(phoneNumber)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeValue(downPayment)
        parcel.writeString(paymentMethod)
        parcel.writeValue(oneDayService)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReqCreateBooking> {
        override fun createFromParcel(parcel: Parcel): ReqCreateBooking {
            return ReqCreateBooking(parcel)
        }

        override fun newArray(size: Int): Array<ReqCreateBooking?> {
            return arrayOfNulls(size)
        }
    }

}
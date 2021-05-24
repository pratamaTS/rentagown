package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ReqCreateBooking (

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("id_address")
    val addressId: String? = null,

    @SerializedName("id_dest_bank")
    val bankId: String? = null,

    @SerializedName("phone")
    val phoneNumber: String? = null,

    @SerializedName("start_date")
    val startDate: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

    @SerializedName("payment_type")
    val paymentMethod: Int? = null,

    @SerializedName("one_day_service")
    val oneDayService: Int? = null,

    @SerializedName("booking_type")
    val bookingType: Int? = null

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(addressId)
        parcel.writeString(bankId)
        parcel.writeString(phoneNumber)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeValue(paymentMethod)
        parcel.writeValue(oneDayService)
        parcel.writeValue(bookingType)
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
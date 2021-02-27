package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Address(

    @SerializedName("id_address")
    val addressId: String? = null,

    @SerializedName("id_user")
    val userId: String? = null,

    @SerializedName("name")
    val receiverName: String? = null,

    @SerializedName("phone")
    val receiverPhoneNumber: String? = null,

    @SerializedName("address_label")
    val addressLabel: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("address_detail")
    val addressDetail: String? = null,


) : BaseModel(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(addressId)
        parcel.writeString(userId)
        parcel.writeString(receiverName)
        parcel.writeString(receiverPhoneNumber)
        parcel.writeString(addressLabel)
        parcel.writeString(address)
        parcel.writeString(addressDetail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }


}
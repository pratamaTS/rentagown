package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ReqSetAddress (

    @SerializedName("id_address")
    val addressId: String? = null

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(addressId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReqSetAddress> {
        override fun createFromParcel(parcel: Parcel): ReqSetAddress {
            return ReqSetAddress(parcel)
        }

        override fun newArray(size: Int): Array<ReqSetAddress?> {
            return arrayOfNulls(size)
        }
    }

}
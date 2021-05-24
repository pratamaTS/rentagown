package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Bank(

    @SerializedName("id_bank")
    val bankId: String? = null,

    @SerializedName("bank_name")
    val bankName: String? = null,

    @SerializedName("account_number")
    val accountNumber: String? = null,

    @SerializedName("account_name")
    val accountName: String? = null,

    @SerializedName("is_default")
    val isDefault: Int = 0,

    @SerializedName("path_photo")
    val photoPath: String? = null

) : BaseModel(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bankId)
        parcel.writeString(bankName)
        parcel.writeString(accountNumber)
        parcel.writeString(accountName)
        parcel.writeInt(isDefault)
        parcel.writeString(photoPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bank> {
        override fun createFromParcel(parcel: Parcel): Bank {
            return Bank(parcel)
        }

        override fun newArray(size: Int): Array<Bank?> {
            return arrayOfNulls(size)
        }
    }

}
package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ProductImage (

    @SerializedName("id_photo")
    val photoId: String? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("path_photo")
    val photoPath: String? = null,

) : BaseModel(), Parcelable {
    
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(photoId)
        parcel.writeString(productId)
        parcel.writeString(photoPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductImage> {
        override fun createFromParcel(parcel: Parcel): ProductImage {
            return ProductImage(parcel)
        }

        override fun newArray(size: Int): Array<ProductImage?> {
            return arrayOfNulls(size)
        }
    }
}

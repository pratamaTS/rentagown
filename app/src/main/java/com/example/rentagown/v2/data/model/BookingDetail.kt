package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BookingDetail (

    @SerializedName("id_transaction")
    val transactionId: String? = null,

    @SerializedName("invoice")
    val invoice: String? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("product_name")
    val productName: String? = null,

    @SerializedName("product_final_price")
    val productPrice: Long? = null,

    @SerializedName("promo_name")
    val promoName: String? = null,

    @SerializedName("product_promo_amount")
    val promoAmount: Long? = null,

    @SerializedName("final_price")
    val finalPrice: Long? = null,

    @SerializedName("id_product_category")
    val productIdCategory: String? = null,

    @SerializedName("product_category")
    val productCategoryName: String? = null,

    @SerializedName("id_fitting")
    val fittingId: String? = null,

    @SerializedName("path_photo")
    val pathPhoto: String? = null

) : BaseModel(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(transactionId)
        parcel.writeString(invoice)
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeValue(productPrice)
        parcel.writeString(promoName)
        parcel.writeValue(promoAmount)
        parcel.writeValue(finalPrice)
        parcel.writeString(productIdCategory)
        parcel.writeString(productCategoryName)
        parcel.writeString(fittingId)
        parcel.writeString(pathPhoto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookingDetail> {
        override fun createFromParcel(parcel: Parcel): BookingDetail {
            return BookingDetail(parcel)
        }

        override fun newArray(size: Int): Array<BookingDetail?> {
            return arrayOfNulls(size)
        }
    }
}
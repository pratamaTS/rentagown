package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Product (

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("product_name")
    val productName: String? = null,

    @SerializedName("product_price")
    val productPrice: Long? = 0,

    @SerializedName("product_desc")
    val productDesc: String? = null,

    @SerializedName("product_status")
    val productStatus: Int? = 0,

    @SerializedName("product_quantity")
    val productQuantity: Long? = 0,

    @SerializedName("id_product_category")
    val productCategoryId: String? = null,

    @SerializedName("name_product_category")
    val productCategoryName: String? = null,

    @SerializedName("id_promo")
    val promoId: String? = null,

    @SerializedName("promo_code")
    val promoCode: String? = null,

    @SerializedName("promo_name")
    val promoName: String? = null,

    @SerializedName("promo_amount")
    val promoAmount: Long? = 0,

    @SerializedName("promo_start")
    val promoStart: String? = null,

    @SerializedName("promo_exp")
    val promoExp: String? = null,

    @SerializedName("final_price")
    val finalPrice: Long? = 0,

    @SerializedName("id_user")
    val userId: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("path_photo")
    val photoPath: String? = null,

//    @SerializedName("Rating")
//    val rating: String? = null,

    @SerializedName("Photo")
    val images: List<ProductImage>? = null,

    @SerializedName("is_wishlist")
    val isWishList: Int? = 0

) : BaseModel(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(ProductImage),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeValue(productPrice)
        parcel.writeString(productDesc)
        parcel.writeValue(productStatus)
        parcel.writeValue(productQuantity)
        parcel.writeString(productCategoryId)
        parcel.writeString(productCategoryName)
        parcel.writeString(promoId)
        parcel.writeString(promoCode)
        parcel.writeString(promoName)
        parcel.writeValue(promoAmount)
        parcel.writeString(promoStart)
        parcel.writeString(promoExp)
        parcel.writeValue(finalPrice)
        parcel.writeString(userId)
        parcel.writeString(name)
        parcel.writeString(photoPath)
        parcel.writeTypedList(images)
        parcel.writeValue(isWishList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}
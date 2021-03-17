package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MasterBank(

	@field:SerializedName("payment_method_type")
	val paymentMethodType: Int? = null,

	@field:SerializedName("path_image")
	val pathImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id_mst_bank")
	val idMstBank: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("payment_method_name")
	val paymentMethodName: String? = null,

	@field:SerializedName("status")
	val status: Int? = null

): BaseModel(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(paymentMethodType)
		parcel.writeString(pathImage)
		parcel.writeString(name)
		parcel.writeString(idMstBank)
		parcel.writeString(displayName)
		parcel.writeString(paymentMethodName)
		parcel.writeValue(status)
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

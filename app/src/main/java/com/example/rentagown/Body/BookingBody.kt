package com.example.rentagown.Body

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BookingBody(

	@field:SerializedName("paid_price")
	val paidPrice: Int? = null,

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("id_address")
	val idAddress: String? = null,

	@field:SerializedName("id_product")
	val idProduct: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("down_payment")
	val downPayment: Int? = null,

	@field:SerializedName("payment_method")
	val paymentMethod: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(paidPrice)
		parcel.writeString(endDate)
		parcel.writeString(idAddress)
		parcel.writeString(idProduct)
		parcel.writeString(phone)
		parcel.writeValue(downPayment)
		parcel.writeString(paymentMethod)
		parcel.writeString(startDate)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BookingBody> {
		override fun createFromParcel(parcel: Parcel): BookingBody {
			return BookingBody(parcel)
		}

		override fun newArray(size: Int): Array<BookingBody?> {
			return arrayOfNulls(size)
		}
	}
}

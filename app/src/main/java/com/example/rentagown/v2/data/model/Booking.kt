package com.example.rentagown.v2.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Booking (

    @SerializedName("id_transaction")
    val transactionId: String? = null,

    @SerializedName("last_payment_invoice")
    val invoice: String? = null,

    @SerializedName("product_final_price")
    val paidPrice: Long? = null,

    @SerializedName("id_product")
    val productId: String? = null,

    @SerializedName("product_name")
    val productName: String? = null,

    @SerializedName("product_category")
    val productCategory: String? = null,

    @SerializedName("product_path_photo")
    val photoPath: String? = null,

    @SerializedName("id_user")
    val userId: String? = null,

    @SerializedName("receiver_name")
    val name: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("address_detail")
    val addressDetail: String? = null,

    @SerializedName("bank_dest_name")
    val bankName: String? = null,

    @SerializedName("account_dest_number")
    val accountNumber: String? = null,

    @SerializedName("account_dest_name")
    val accountName: String? = null,

    @SerializedName("bank_dest_path_photo")
    val bankPathPhoto: String? = null,

    @SerializedName("receiver_phone")
    val phone: String? = null,

    @SerializedName("start_date")
    val startDate: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

    @SerializedName("one_day_service")
    val oneDayService: String? = null,

    @SerializedName("down_payment")
    val downPayment: Long? = null,

    @SerializedName("full_payment")
    val fullPayment: Long? = null,

    @SerializedName("forfeit")
    val forfeit: Int? = null,

    @SerializedName("payment_type")
    val paymentMethod: Int? = null,

    @SerializedName("payment_type_name")
    val paymentMethodName: String? = null,

    @SerializedName("last_payment_method")
    val lastPaymentMethod: Int? = null,

    @SerializedName("last_payment_method_name")
    val lastPaymentMethodName: String? = null,

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("status_name")
    val statusTransaction: String? = null,

    @SerializedName("status_payment")
    val paymentStatus: String? = null,

    @SerializedName("remaining_bills")
    val remainingBills: Long? = null,

    @SerializedName("last_payment_deadline")
    val paymentDeadline: String? = null,

    @SerializedName("payment_bank_name")
    val paymentBankName: String? = null,

    @SerializedName("payment_account_number")
    val paymentAccountNumber: String? = null,

    @SerializedName("payment_account_name")
    val paymentAccountName: String? = null,

    @SerializedName("payment_amount")
    val paymentAmount: Long? = null,

    @SerializedName("last_payment_amount")
    val lastPaymentAmount: Long? = null,

    @SerializedName("next_payment_amount")
    val nextPaymentAmount: Long? = null,

    @SerializedName("repayment_bank_name")
    val repaymentBankName: String? = null,

    @SerializedName("repayment_account_number")
    val repaymentAccoungNumber: String? = null,

    @SerializedName("repayment_account_name")
    val repaymentAccountName: String? = null,

    @SerializedName("repayment_amount")
    val repaymentAmount: Long? = null,

    @SerializedName("total_bill_amount")
    val totalBillAmount: Long? = null,

    @SerializedName("id_fitting")
    val fittingId: String? = null,

    @SerializedName("id_rating")
    val ratingId: String? = null,

    @SerializedName("fcm_id")
    val fcmId: String? = null,

    @SerializedName("able_fitting")
    val ableToFitting: Int? = null,

    @SerializedName("able_rate")
    val ableToRating: Int? = null,

    @SerializedName("able_pay")
    val ableToPay: Int? = null,

    @SerializedName("created_at")
    val bookingNow: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(transactionId)
        parcel.writeString(invoice)
        parcel.writeValue(paidPrice)
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(productCategory)
        parcel.writeString(photoPath)
        parcel.writeString(userId)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(addressDetail)
        parcel.writeString(bankName)
        parcel.writeString(accountNumber)
        parcel.writeString(accountName)
        parcel.writeString(bankPathPhoto)
        parcel.writeString(phone)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(oneDayService)
        parcel.writeValue(downPayment)
        parcel.writeValue(fullPayment)
        parcel.writeValue(forfeit)
        parcel.writeValue(paymentMethod)
        parcel.writeString(paymentMethodName)
        parcel.writeValue(lastPaymentMethod)
        parcel.writeString(lastPaymentMethodName)
        parcel.writeValue(status)
        parcel.writeString(statusTransaction)
        parcel.writeString(paymentStatus)
        parcel.writeValue(remainingBills)
        parcel.writeString(paymentDeadline)
        parcel.writeString(paymentBankName)
        parcel.writeString(paymentAccountNumber)
        parcel.writeString(paymentAccountName)
        parcel.writeValue(paymentAmount)
        parcel.writeValue(lastPaymentAmount)
        parcel.writeValue(nextPaymentAmount)
        parcel.writeString(repaymentBankName)
        parcel.writeString(repaymentAccoungNumber)
        parcel.writeString(repaymentAccountName)
        parcel.writeValue(repaymentAmount)
        parcel.writeValue(totalBillAmount)
        parcel.writeString(fittingId)
        parcel.writeString(ratingId)
        parcel.writeString(fcmId)
        parcel.writeValue(ableToFitting)
        parcel.writeValue(ableToRating)
        parcel.writeValue(ableToPay)
        parcel.writeString(bookingNow)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Booking> {
        override fun createFromParcel(parcel: Parcel): Booking {
            return Booking(parcel)
        }

        override fun newArray(size: Int): Array<Booking?> {
            return arrayOfNulls(size)
        }
    }

}














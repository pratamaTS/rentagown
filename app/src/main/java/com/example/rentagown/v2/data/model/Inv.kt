package com.example.rentagown.v2.data.model

import com.google.gson.annotations.SerializedName

data class Inv(

	@field:SerializedName("payment_dest_bank_logo")
	val paymentDestBankLogo: String? = null,

	@field:SerializedName("payment_method_type")
	val paymentMethodType: Int? = null,

	@field:SerializedName("payment_status_name")
	val paymentStatusName: String? = null,

	@field:SerializedName("booking")
	val booking: Booking? = null,

	@field:SerializedName("notes")
	val notes: String? = null,

	@field:SerializedName("payment_source_acc_number")
	val paymentSourceAccNumber: String? = null,

	@field:SerializedName("payment_source_bank_logo")
	val paymentSourceBankLogo: String? = null,

	@field:SerializedName("payment_date_time")
	val paymentDateTime: String? = null,

	@field:SerializedName("id_transaction")
	val idTransaction: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("invoice_amount")
	val invoiceAmount: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("payment_dest_bank_id")
	val paymentDestBankId: String? = null,

	@field:SerializedName("payment_source_bank_id")
	val paymentSourceBankId: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("payment_dest_acc_number")
	val paymentDestAccNumber: String? = null,

	@field:SerializedName("payment_dest_bank_name")
	val paymentDestBankName: String? = null,

	@field:SerializedName("payment_source_acc_name")
	val paymentSourceAccName: String? = null,

	@field:SerializedName("payment_source_bank_name")
	val paymentSourceBankName: String? = null,

	@field:SerializedName("payment_status")
	val paymentStatus: Int? = null,

	@field:SerializedName("payment_amount")
	val paymentAmount: Int? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("payment_method_name")
	val paymentMethodName: String? = null,

	@field:SerializedName("payment_deadline")
	val paymentDeadline: String? = null,

	@field:SerializedName("payment_dest_acc_name")
	val paymentDestAccName: String? = null,

	@field:SerializedName("invoice")
	val invoice: String? = null
)

data class Booking1(

	@field:SerializedName("product_path_photo")
	val productPathPhoto: String? = null,

	@field:SerializedName("product_promo_amount_percent")
	val productPromoAmountPercent: Int? = null,

	@field:SerializedName("full_payment")
	val fullPayment: Int? = null,

	@field:SerializedName("bank_dest_path_photo")
	val bankDestPathPhoto: String? = null,

	@field:SerializedName("id_transaction")
	val idTransaction: String? = null,

	@field:SerializedName("last_payment_invoice")
	val lastPaymentInvoice: String? = null,

	@field:SerializedName("address_label")
	val addressLabel: String? = null,

	@field:SerializedName("remaining_bills")
	val remainingBills: Int? = null,

	@field:SerializedName("product_promo_amount")
	val productPromoAmount: Int? = null,

	@field:SerializedName("last_payment_invoice_amount")
	val lastPaymentInvoiceAmount: Int? = null,

	@field:SerializedName("last_payment_method")
	val lastPaymentMethod: Int? = null,

	@field:SerializedName("notes_address")
	val notesAddress: String? = null,

	@field:SerializedName("down_payment")
	val downPayment: Int? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("able_rate")
	val ableRate: Int? = null,

	@field:SerializedName("id_fitting")
	val idFitting: String? = null,

	@field:SerializedName("payment_type")
	val paymentType: Int? = null,

	@field:SerializedName("id_product")
	val idProduct: String? = null,

	@field:SerializedName("account_dest_name")
	val accountDestName: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("last_payment_status")
	val lastPaymentStatus: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("able_pay")
	val ablePay: Int? = null,

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("last_payment_account_name")
	val lastPaymentAccountName: String? = null,

	@field:SerializedName("last_payment_method_name")
	val lastPaymentMethodName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("notes_booking")
	val notesBooking: String? = null,

	@field:SerializedName("id_rating")
	val idRating: String? = null,

	@field:SerializedName("last_payment_bank_name")
	val lastPaymentBankName: String? = null,

	@field:SerializedName("address_detail")
	val addressDetail: String? = null,

	@field:SerializedName("id_address")
	val idAddress: String? = null,

	@field:SerializedName("account_dest_number")
	val accountDestNumber: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id_dest_bank")
	val idDestBank: String? = null,

	@field:SerializedName("receiver_name")
	val receiverName: String? = null,

	@field:SerializedName("one_day_service")
	val oneDayService: Int? = null,

	@field:SerializedName("last_payment_deadline")
	val lastPaymentDeadline: String? = null,

	@field:SerializedName("bank_dest_name")
	val bankDestName: String? = null,

	@field:SerializedName("fcm_id")
	val fcmId: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("product_final_price")
	val productFinalPrice: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("payment_type_name")
	val paymentTypeName: String? = null,

	@field:SerializedName("status_name")
	val statusName: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("last_payment_date_time")
	val lastPaymentDateTime: Any? = null,

	@field:SerializedName("last_payment_amount")
	val lastPaymentAmount: Int? = null,

	@field:SerializedName("able_fitting")
	val ableFitting: Int? = null,

	@field:SerializedName("last_payment_status_name")
	val lastPaymentStatusName: String? = null,

	@field:SerializedName("last_payment_account_number")
	val lastPaymentAccountNumber: String? = null,

	@field:SerializedName("receiver_phone")
	val receiverPhone: String? = null,

	@field:SerializedName("product_category")
	val productCategory: String? = null,

	@field:SerializedName("next_payment_amount")
	val nextPaymentAmount: Int? = null
)

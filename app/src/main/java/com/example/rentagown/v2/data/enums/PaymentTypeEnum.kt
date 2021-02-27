package com.example.rentagown.v2.data.enums

enum class PaymentTypeEnum(
        val typeId: String,
        val typeName: String,
        val dpValue: Long,
        val isFullPayment: Boolean) {

    DOWN_PAYMENT("Deposit Booking", "Deposit Booking (Transfer)", 500000, false),
    FULL_PAYMENT("Full Payment", "Full Payment (Transfer)", 0, true);

    companion object {

        fun getDefaultPaymentType(): PaymentTypeEnum {
            return DOWN_PAYMENT
        }

        fun getByOrdinal(ordinal: Int?): PaymentTypeEnum? {
            if(ordinal == null || ordinal < 0) return null

            return values()[ordinal]
        }

    }

}
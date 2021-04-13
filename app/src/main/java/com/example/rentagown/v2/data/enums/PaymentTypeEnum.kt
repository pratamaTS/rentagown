package com.example.rentagown.v2.data.enums

enum class PaymentTypeEnum(
        val typeId: Int,
        val typeName: String,
        val dpValue: Long,
        val isFullPayment: Boolean) {

    DOWN_PAYMENT(1, "Down Payment (Transfer)", 1000000, false),
    FULL_PAYMENT(2, "Full Payment (Transfer)", 0, true);

    companion object {

        fun getDefaultPaymentType(): PaymentTypeEnum {
            return DOWN_PAYMENT
        }

        fun getByOrdinal(ordinal: Int?): PaymentTypeEnum? {
            if(ordinal == null || ordinal < 0) return null

            return values()[ordinal]
        }

        fun getByTypeId(typeId: Int?): PaymentTypeEnum? {
            return values().find { pt -> pt.typeId == typeId }
        }

    }

}
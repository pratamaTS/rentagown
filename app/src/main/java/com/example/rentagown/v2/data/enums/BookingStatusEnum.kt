package com.example.rentagown.v2.data.enums

enum class BookingStatusEnum(
    val statusId: Int,
    val statusName: String,
    val reason: String
) {

    WAITING_DOWN_PAYMENT(1, "Waiting Down Payment", ""),
    WAITING_FULL_PAYMENT(1, "Waiting Full Payment", ""),
    WAITING_FULL_PAYMENT_DP(3, "Waiting Full Payment", ""),
    WAITING_CONFIRMATION(2, "Waiting Confirmation", ""),
    WAITING_PAYMENT_CONFIRMATION(4, "Waiting Payment Confirmation", ""),
    ON_PROCESS(5, "On Process", ""),
    SUCCESS(6, "Success", ""),
    ABLE_TO_PAY(1, "Able to Pay", ""),
    ABLE_TO_FITTING(1, "Able to Fitting", ""),
    ABLE_TO_RATING(1, "Able to Rating", ""),
    CANCELLED_BY_USER(21, "Cancelled", ""),
    CANCELLED_BY_SYSTEM(22, "Cancelled by admin", "cancelled by admin");

    companion object {

        fun isWaitingForPayment(statusId: Int?): Boolean {
            if(statusId == WAITING_DOWN_PAYMENT.statusId || statusId == WAITING_FULL_PAYMENT.statusId || statusId == WAITING_FULL_PAYMENT_DP.statusId) return true

            return false
        }

        fun isWaitingForConfirmation(statusId: Int?): Boolean {
            if(statusId == WAITING_CONFIRMATION.statusId || statusId == WAITING_PAYMENT_CONFIRMATION.statusId) return true

            return false
        }

        fun isOnGoing(statusId: Int?): Boolean {
            if(statusId == ON_PROCESS.statusId) return true

            return false
        }

        fun isAbleToPay(ablePay: Int?): Boolean {
            if(ablePay == ABLE_TO_PAY.statusId) return true

            return false
        }

        fun isAbleToFitting(ableFitting: Int?): Boolean {
            if(ableFitting == ABLE_TO_FITTING.statusId) return true

            return false
        }

        fun isAbleToRating(ableRating: Int?): Boolean {
            if(ableRating == ABLE_TO_RATING.statusId) return true

            return false
        }

        fun isCancelled(statusId: Int?): Boolean {
            if(statusId == CANCELLED_BY_USER.statusId || statusId == CANCELLED_BY_SYSTEM.statusId) return true

            return false
        }

        fun isCompleted(status: Int?): Boolean {
            return status == SUCCESS.statusId
        }

        fun getByStatusId(statusId: Int?): BookingStatusEnum? {
            return BookingStatusEnum.values().find { e -> e.statusId == statusId }
        }

    }

}
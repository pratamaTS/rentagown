package com.example.rentagown.v2.data.enums

enum class BookingStatusEnum(
    val statusId: Int,
    val statusName: String,
    val reason: String
) {

    WAITING_PAYMENT(1, "Waiting Payment", ""),
    ON_PROCESS(2, "On Process", ""),
    SUCCESS(3, "Done", ""),
    CANCELLED_BY_USER(4, "Cancelled", ""),
    CANCELLED_BY_SYSTEM(5, "Cancelled", "cancelled by user");

    companion object {

        fun isOnGoing(statusId: Int?): Boolean {
            if(statusId == WAITING_PAYMENT.statusId || statusId == ON_PROCESS.statusId) return true

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
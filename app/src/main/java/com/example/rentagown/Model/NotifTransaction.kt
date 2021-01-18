package com.example.rentagown.Model

class NotifTransaction(
    var dateTransaction: String,
    var itemName: String,
    var dateBook: String,
    var timeBook: String,
    var statusPayment: String,
    var price: String,
    var statusNotif: String
) {

    fun setStatusHistory(statusHistory: String?) {
        statusNotif = statusNotif
    }
}
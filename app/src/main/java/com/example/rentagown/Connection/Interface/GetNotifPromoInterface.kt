package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Notification.DataNotifPromo

interface GetNotifPromoInterface {
    fun onSuccessGetNotifPromo(dataNotifPromo: ArrayList<DataNotifPromo>?)
    fun onErrorGetNotifPromo(msg: String)
}
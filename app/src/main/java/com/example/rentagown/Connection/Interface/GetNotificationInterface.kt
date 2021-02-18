package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Notification.DataNotification

interface GetNotificationInterface {
    fun onSuccessGetNotification(dataNotification: ArrayList<DataNotification>?)
    fun onErrorGetNotification(msg: String)
}
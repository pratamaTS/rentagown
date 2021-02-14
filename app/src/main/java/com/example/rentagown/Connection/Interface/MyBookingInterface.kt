package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.MyBooking.DataMyBooking

interface MyBookingInterface {
    fun onSuccessGetMyBooking(dataMyBooking: DataMyBooking?)
    fun onErrorGetMyBooking(msg: String)
}
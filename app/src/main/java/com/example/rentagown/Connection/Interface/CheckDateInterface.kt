package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.CheckDate.DataCheckDate

interface CheckDateInterface {
    fun onSuccessCheckDate(dataCheckDate: DataCheckDate)
    fun onErrorCheckDate(msg: String)
}
package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.GetBank.DataBank

interface GetBankInterface {
    fun onSuccessGetBank(dataBank: ArrayList<DataBank>?)
    fun onErrorGetBank(msg: String)
}
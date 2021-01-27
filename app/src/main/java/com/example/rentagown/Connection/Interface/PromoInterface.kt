package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Promo.DataPromo

interface PromoInterface {
    fun onSuccessGetPromo(dataPromo: ArrayList<DataPromo>?)
    fun onErrorGetPromo(msg: String)
}
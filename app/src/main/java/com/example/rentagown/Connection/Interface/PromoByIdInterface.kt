package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Promo.PromoDetail.DataPromoDetail

interface PromoByIdInterface {
    fun onSuccessGetPromoById(dataPromoById: DataPromoDetail?)
    fun onErrorGetPromoById(msg: String)
}
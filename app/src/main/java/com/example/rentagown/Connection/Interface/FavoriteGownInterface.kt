package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.FavoriteGown.DataFavoriteGown

interface FavoriteGownInterface {
    fun onSuccessGetFavoriteGown(dataFavoriteGown: ArrayList<DataFavoriteGown?>?)
    fun onErrorGetFavoriteGown(msg: String)
}
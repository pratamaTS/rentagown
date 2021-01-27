package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.NewGown.DataNewGown

interface NewGownInterface {
    fun onSuccessGetNewGown(dataNewGown: ArrayList<DataNewGown?>?)
    fun onErrorGetNewGown(msg: String)
}
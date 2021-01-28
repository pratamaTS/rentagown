package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Profile.DataProfile

interface ProfileInterface {
    fun onSuccessGetPromo(dataProfile: DataProfile?)
    fun onErrorGetPromo(msg: String)
}
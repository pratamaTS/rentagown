package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.Profile.DataProfile

interface ProfileInterface {
    fun onSuccessGetProfile(dataProfile: DataProfile?)
    fun onErrorGetProfile(msg: String)
}
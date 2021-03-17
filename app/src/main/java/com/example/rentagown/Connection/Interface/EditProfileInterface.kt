package com.example.rentagown.Connection.Interface

import com.example.rentagown.Response.EditProfile.DataEditProfile

interface EditProfileInterface {
    fun onSuccessEditProfile(dataEditProfile: DataEditProfile)
    fun onErrorEditProfile(msg: String)
}
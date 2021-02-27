package com.example.rentagown.Connection

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.rentagown.R

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        private const val USER_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"

        private const val USER_PHONE_NUMBER = "user_phone_number"
        private const val USER_NAME = "user_name"
    }

    /**
     * Function to save auth token
     */
    @SuppressLint("ApplySharedPref")
    fun logOut() {
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }

    /**
     * Function to save auth token
     */
    @SuppressLint("ApplySharedPref")
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.commit()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun isLoggedIn() = !prefs.getString(USER_TOKEN, null).isNullOrBlank()

    fun saveUserName(userName: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, userName)
        editor.apply()
    }

    fun fetchUserName() = prefs.getString(USER_TOKEN, null)

    fun savePhoneNumber(phoneNumber: String) {
        val editor = prefs.edit()
        editor.putString(USER_PHONE_NUMBER, phoneNumber)
        editor.apply()
    }

    fun fetchPhoneNumber() = prefs.getString(USER_PHONE_NUMBER, null)

}
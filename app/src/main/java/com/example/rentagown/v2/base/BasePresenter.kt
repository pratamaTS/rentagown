package com.example.rentagown.v2.base

interface BasePresenter {

    fun start()
    fun attachView(mView: Any?)
    fun detachView()
    fun cleanUp()
    fun onBackPressed()


}
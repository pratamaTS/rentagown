package com.example.rentagown.v2.ui.bookingsuccess

import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity

class BookingSuccessActivity : BaseRAGActivity<BookingSuccessContract.Presenter>(), BookingSuccessContract.View,
        View.OnClickListener {

    override val layoutId: Int = R.layout.activity_booking_success_v2

    override lateinit var presenter: BookingSuccessContract.Presenter

    private lateinit var btnBackToHome: Button

    override fun init() {
        super.init()

        presenter = BookingSuccessPresenter()
    }

    override fun setupWidgets() {
        super.setupWidgets()

        btnBackToHome = findViewById(R.id.btn_back_to_home)
        btnBackToHome.setOnClickListener(this)

    }

    override fun navigateBackToHome() {
        Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back_to_home -> presenter.onBtnBackToHomeClicked()
        }
    }

}
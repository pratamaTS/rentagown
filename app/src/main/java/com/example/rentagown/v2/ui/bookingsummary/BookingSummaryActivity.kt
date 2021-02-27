package com.example.rentagown.v2.ui.bookingsummary

import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity

class BookingSummaryActivity : BaseRAGActivity<BookingSummaryContract.Presenter>(), BookingSummaryContract.View,
        View.OnClickListener {

    override val layoutId: Int = R.layout.activity_booking_summary_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: BookingSummaryContract.Presenter

    private lateinit var btnConfirmPayment: Button

    override fun init() {
        presenter = BookingSummaryPresenter()
    }

    override fun setupWidgets() {
        super.setupWidgets()

        btnConfirmPayment = findViewById(R.id.btn_confirm_payment)

        btnConfirmPayment.setOnClickListener(this)
    }

    override fun book() {

    }

    override fun navigateToConfirmPayment() {
        Intent(this, ConfirmPaymentActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm_payment -> presenter.onBtnConfirmPaymentClicked()
        }
    }

}
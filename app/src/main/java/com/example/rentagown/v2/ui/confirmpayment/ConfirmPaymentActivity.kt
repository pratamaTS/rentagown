package com.example.rentagown.v2.ui.confirmpayment

import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.ui.bookingsuccess.BookingSuccessActivity

class ConfirmPaymentActivity : BaseRAGActivity<ConfirmPaymentContract.Presenter>(), ConfirmPaymentContract.View,
        View.OnClickListener {

    override val layoutId: Int = R.layout.activity_confirm_payment_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: ConfirmPaymentContract.Presenter

    private lateinit var btnConfirm: Button

    override fun init() {
        super.init()

        presenter = ConfirmPaymentPresenter()
    }

    override fun setupWidgets() {
        super.setupWidgets()

        btnConfirm = findViewById(R.id.btn_confirm)

        btnConfirm.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm -> presenter.onBtnConfirmClicked()
        }
    }

    override fun navigateToBookingSuccess() {
        Intent(this, BookingSuccessActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }


}
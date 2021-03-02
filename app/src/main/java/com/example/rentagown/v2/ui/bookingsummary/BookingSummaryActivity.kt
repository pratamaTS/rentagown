package com.example.rentagown.v2.ui.bookingsummary

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bookingsuccess.BookingSuccessActivity
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity
import com.example.rentagown.v2.util.Utils

class BookingSummaryActivity : BaseRAGActivity<BookingSummaryContract.Presenter>(), BookingSummaryContract.View,
        View.OnClickListener {

    override val layoutId: Int = R.layout.activity_booking_summary_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: BookingSummaryContract.Presenter

    private lateinit var tvPaymentDeadline: TextView
    private lateinit var tvCountdownTimer: TextView

    private lateinit var ivPaymentBankLogo: ImageView
    private lateinit var tvConfirmAccountNumber: TextView
    private lateinit var tvConfirmAccountName: TextView

    private lateinit var btnInvoice: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var tvPaymentType: TextView

    private lateinit var btnCancelTransaction: Button
    private lateinit var btnConfirmPayment: Button
    private lateinit var btnBackToHome: Button

    override fun init() {
        presenter = BookingSummaryPresenter(RepositoryLocator
                .getInstance(RemoteRepositoryLocator
                        .getInstance(RAGApi
                                .apiService(this)))
                .bookingRepository)
    }

    override fun setupWidgets() {
        super.setupWidgets()

        tvPaymentDeadline = findViewById(R.id.tv_payment_deadline)
        tvCountdownTimer = findViewById(R.id.tv_countdown_timer)

        ivPaymentBankLogo = findViewById(R.id.iv_payment_bank_logo)
        tvConfirmAccountNumber = findViewById(R.id.tv_confirm_account_number)
        tvConfirmAccountName = findViewById(R.id.tv_confirm_account_name)

        btnInvoice = findViewById(R.id.btn_invoice)
        tvTotalPrice = findViewById(R.id.tv_total_price)
        tvPaymentType = findViewById(R.id.tv_payment_type)

        btnCancelTransaction = findViewById(R.id.btn_cancel_transaction)
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment)
        btnBackToHome = findViewById(R.id.btn_back_to_home)

        btnCancelTransaction.setOnClickListener(this)
        btnConfirmPayment.setOnClickListener(this)
        btnBackToHome.setOnClickListener(this)
    }


    override fun navigateToConfirmPayment(booking: Booking) {
        Intent(this, ConfirmPaymentActivity::class.java).apply {
            putExtra("booking", booking)
            putExtra("go_to_booking_success", true)
            startActivityForResult(this, ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT)
        }
    }

    override fun setDataBookingToView(booking: Booking) {
        Glide.with(this)
                .load(BuildConfig.BASE_PHOTO_URL + booking.bankPathPhoto)
                .listener(Utils.getGlideException())
                .fitCenter()
                .error(R.color.colorGray)
                .into(ivPaymentBankLogo)

        val paymentDeadlineDt = Utils.parseDateTime(booking.paymentDeadline, Utils.DATE_FORMAT_PAYMENT_DEADLINE)
        tvPaymentDeadline.text = Utils.formatDateTime(booking.paymentDeadline, Utils.DATE_FORMAT_PAYMENT_DEADLINE, Utils.DATE_TIME_FORMAT_PAYMENT_DEADLINE)

        tvTotalPrice.text = Utils.formatMoney(booking.paidPrice)
        tvConfirmAccountNumber.text = booking.accountNumber
        tvConfirmAccountName.text = booking.accountName

        tvPaymentType.text = booking.paymentMethod

    }

    override fun getBookingData(): Booking? = intent.getParcelableExtra("booking")

    override fun showMsgSuccessCancelBooking() {
        showMessage(getString(R.string.msg_success_cancel_booking))
    }

    override fun showMsgBookingNotFound() {
        showMessage(getString(R.string.err_booking_not_found))
    }

    override fun navigateToBookingSuccess() {
        Intent(this, BookingSuccessActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm_payment -> presenter.onBtnConfirmPaymentClicked()
            R.id.btn_back_to_home -> presenter.onBtnBackToHomeClicked()
            R.id.btn_cancel_transaction -> presenter.onBtnCancelTransactionClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT) {
            if(resultCode == ConfirmPaymentActivity.RES_CONFIRM_PAYMENT_SUCCESS) {
                presenter.onPaymentConfirmed(intent.getParcelableExtra("booking"))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
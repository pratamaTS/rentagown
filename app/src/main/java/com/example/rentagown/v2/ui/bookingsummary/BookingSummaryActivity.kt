package com.example.rentagown.v2.ui.bookingsummary

import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class BookingSummaryActivity : BaseRAGActivity<BookingSummaryContract.Presenter>(), BookingSummaryContract.View,
        View.OnClickListener {

    override val layoutId: Int = R.layout.activity_booking_summary_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: BookingSummaryContract.Presenter

    private lateinit var tvPaymentDeadlineDate: TextView
    private lateinit var tvPaymentDeadlineTime: TextView
//    private lateinit var tvCountdownTimer: TextView

    private lateinit var ivPaymentBankLogo: ImageView
    private lateinit var tvConfirmAccountNumber: TextView
    private lateinit var tvConfirmAccountName: TextView

    private lateinit var btnInvoice: TextView
//    private lateinit var tvTotalPrice: TextView
//    private lateinit var tvPaymentType: TextView
    private lateinit var tvThanksConfirm: TextView
    private lateinit var tvBookingDate: TextView
    private lateinit var tvGownName: TextView
    private lateinit var tvStartDate: TextView
    private lateinit var tvEndDate: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDownPayment: TextView
    private lateinit var tvFullPayment: TextView
    private lateinit var tvRemainningBills: TextView
    private lateinit var tvTitleDeadlineDate: TextView
    private lateinit var tvTitleDeadlineTime: TextView

    private lateinit var layoutDP: LinearLayout
    private lateinit var layoutFP: LinearLayout

//    private lateinit var btnCancelTransaction: Button
    private lateinit var btnConfirmPayment: Button
    private lateinit var btnBackToHome: Button

    override fun init() {
        presenter = BookingSummaryPresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(
                            RAGApi
                                .apiService(this)
                        )
                )
                .bookingRepository
        )
    }

    override fun setupWidgets() {
        super.setupWidgets()

        tvPaymentDeadlineDate = findViewById(R.id.tv_payment_deadline_date)
        tvPaymentDeadlineTime = findViewById(R.id.tv_payment_deadline_time)
        tvTitleDeadlineDate = findViewById(R.id.tv_title_payment_deadline_date)
        tvTitleDeadlineTime = findViewById(R.id.tv_title_payment_deadline_time)
//        tvCountdownTimer = findViewById(R.id.tv_countdown_timer)

        ivPaymentBankLogo = findViewById(R.id.iv_payment_bank_logo)
        tvConfirmAccountNumber = findViewById(R.id.tv_confirm_account_number)
        tvConfirmAccountName = findViewById(R.id.tv_confirm_account_name)
        tvThanksConfirm = findViewById(R.id.tv_thanks_confirm)
        tvBookingDate = findViewById(R.id.tv_booking_date_cp)
        tvGownName = findViewById(R.id.tv_gown_name_cp)
        tvStartDate = findViewById(R.id.tv_booking_from_cp)
        tvEndDate = findViewById(R.id.tv_booking_to_cp)
        tvPrice = findViewById(R.id.tv_price_cp)
        tvDownPayment = findViewById(R.id.tv_dp_cp)
        tvFullPayment = findViewById(R.id.tv_full_payment_cp)
        tvRemainningBills = findViewById(R.id.tv_remaining_bill_cp)

        layoutDP = findViewById(R.id.layout_dp_cp)
        layoutFP = findViewById(R.id.layout_fp_cp)

        btnInvoice = findViewById(R.id.btn_invoice)
//        tvTotalPrice = findViewById(R.id.tv_total_price)
//        tvPaymentType = findViewById(R.id.tv_payment_type)

//        btnCancelTransaction = findViewById(R.id.btn_cancel_transaction)
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment)
        btnBackToHome = findViewById(R.id.btn_back_to_home)

//        btnCancelTransaction.setOnClickListener(this)
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

        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(booking.paymentDeadline)
        val createdDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX").parse(booking.bookingNow)
        val bookingFrom = SimpleDateFormat("yyyy-MM-dd").parse(booking.startDate)
        val bookingTo = SimpleDateFormat("yyyy-MM-dd").parse(booking.endDate)
        val bookingDateTimeNow = SimpleDateFormat("dd-MM-yyyy").format(createdDate)
        val pDeadlineDate = SimpleDateFormat("dd-MM-yyyy").format(date)
        val pDeadlineTime = SimpleDateFormat("HH:mm:ss").format(date)
        val bookingFromDate = SimpleDateFormat("dd-MM-yyyy").format(bookingFrom)
        val bookingToDate = SimpleDateFormat("dd-MM-yyyy").format(bookingTo)
        val cust = booking.name?.capitalize()?.trim()
        val index = cust?.indexOf(' ')
        var firstName: String?

        if(index != -1) {
            firstName = index?.let { it -> cust.substring(0, it) }
        }else{
            firstName = cust
        }

        tvThanksConfirm.text = "Thank you " + firstName + " for booking Rent a Gown,"
        tvBookingDate.text = bookingDateTimeNow
        tvGownName.text = booking.productName?.capitalize()?.trim()
        tvStartDate.text = bookingFromDate
        tvEndDate.text = bookingToDate
        tvPrice.text = Utils.formatMoney(booking.paidPrice)
        if(booking.paymentMethod == 1){
            layoutFP.visibility = View.GONE
            layoutDP.visibility = View.VISIBLE
            tvDownPayment.text = Utils.formatMoney(booking.downPayment)
            tvTitleDeadlineDate.text = "Down Payment - due date"
            tvTitleDeadlineTime.text = "Down Payment - due time"
        }else{
            layoutDP.visibility = View.GONE
            layoutFP.visibility = View.VISIBLE
            tvFullPayment.text = Utils.formatMoney(booking.paidPrice)
            tvTitleDeadlineDate.text = "Full Payment - due date"
            tvTitleDeadlineTime.text = "Full Payment - due time"
        }
        tvRemainningBills.text = Utils.formatMoney(booking.remainingBills)

//        val timer = object: CountDownTimer(43200000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                val countDown = String.format(
//                    "%02d:%02d:%02d",
//                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
//                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
//                        TimeUnit.MILLISECONDS.toHours(
//                            millisUntilFinished
//                        )
//                    ),
//                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
//                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
//                    )
//                )
//                tvCountdownTimer.visibility = View.VISIBLE
//                tvCountdownTimer.text = countDown
//            }
//
//            override fun onFinish() {
//                tvCountdownTimer.text = "00:00:00"
//            }
//        }
//        timer.start()

        tvPaymentDeadlineDate.text = pDeadlineDate.toString()
        tvPaymentDeadlineTime.text = pDeadlineTime.toString()

//        tvTotalPrice.text = Utils.formatMoney(booking.paidPrice)
        tvConfirmAccountNumber.text = booking.accountNumber
        tvConfirmAccountName.text = booking.accountName

//        tvPaymentType.text = booking.paymentMethodName

    }

    override fun getBookingData(): Booking? = intent.getParcelableExtra("booking")

//    override fun showMsgSuccessCancelBooking() {
//        showMessage(getString(R.string.msg_success_cancel_booking))
//    }

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
//            R.id.btn_cancel_transaction -> presenter.onBtnCancelTransactionClicked()
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
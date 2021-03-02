package com.example.rentagown.v2.ui.bookingdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.enums.BookingStatusEnum
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity
import com.example.rentagown.v2.ui.fittingsize.FittingSizeActivity
import com.example.rentagown.v2.ui.reviewbooking.ReviewBookingActivity
import com.example.rentagown.v2.util.Utils
import kotlin.math.abs

class BookingDetailActivity : BaseRAGActivity<BookingDetailContract.Presenter>(), BookingDetailContract.View,
        View.OnClickListener {

    companion object {

        const val REQ_VIEW_BOOKING_DETAIL = 724
        const val RES_BOOKING_DETAIL_CHANGED = 723

    }

    override val layoutId: Int = R.layout.activity_booking_detail_v2
    override var btnBackId: Int = R.id.btn_back


    override lateinit var presenter: BookingDetailContract.Presenter

    private lateinit var ivProductImage: ImageView

    private lateinit var tvProductName: TextView
    private lateinit var tvProductCategoryName: TextView
    private lateinit var tvBookingStatus: TextView
    private lateinit var tvBookingStartEndDate: TextView
    private lateinit var tvProductName2: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductPrice2: TextView
    private lateinit var tvDiscountAmount: TextView
    private lateinit var tvBookingDpPaid: TextView
    private lateinit var tvTotalPrice: TextView

    private lateinit var containerConfirmPayment: View

    private lateinit var btnPay: Button
    private lateinit var btnAction: Button

    private var resultIntent: Intent? = null

    override fun init() {
        super.init()

        presenter = BookingDetailPresenter(RepositoryLocator.getInstance(RemoteRepositoryLocator.getInstance(RAGApi.apiService(this))).bookingRepository)
    }

    override fun setupWidgets() {
        super.setupWidgets()

        ivProductImage = findViewById(R.id.iv_product_image)

        tvProductName = findViewById(R.id.tv_product_name)
        tvProductCategoryName = findViewById(R.id.tv_product_category_name)
        tvBookingStatus = findViewById(R.id.tv_booking_status)
        tvBookingStartEndDate = findViewById(R.id.tv_booking_start_end_date)
        tvProductName2 = findViewById(R.id.tv_product_name2)
        tvProductPrice = findViewById(R.id.tv_product_price)
        tvProductPrice2 = findViewById(R.id.tv_product_price2)
        tvDiscountAmount = findViewById(R.id.tv_discount_amount)
        tvBookingDpPaid = findViewById(R.id.tv_booking_dp_paid)
        tvTotalPrice = findViewById(R.id.tv_total_price)

        containerConfirmPayment = findViewById(R.id.container_confirm_payment)

        btnPay = findViewById(R.id.btn_pay)
        btnAction = findViewById(R.id.btn_action)

        btnPay.setOnClickListener(this)
        btnAction.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    override fun setDataBookingToView(booking: Booking) {
        Glide.with(this)
                .load(BuildConfig.BASE_PHOTO_URL + booking.photoPath)
                .listener(Utils.getGlideException())
                .centerCrop()
                .error(R.color.colorGray)
                .into(ivProductImage)

        tvProductName.text = booking.productName ?: getString(R.string.lbl_no_text)
        tvProductCategoryName.text = booking.bookingDetail?.productCategoryName ?: getString(R.string.lbl_no_text)
        tvBookingStartEndDate.text = Utils.formatMyBookingStartEndDate(booking.startDate, booking.endDate)
        tvProductName2.text = booking.productName ?: getString(R.string.lbl_no_text)

        when {
            BookingStatusEnum.isCancelled(booking.status) -> {
                tvBookingStatus.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
                tvBookingStatus.background = ContextCompat.getDrawable(this, R.drawable.bg_booking_status_cancelled)
            }
            BookingStatusEnum.isCompleted(booking.status) -> {
                tvBookingStatus.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGreen))
                tvBookingStatus.background = ContextCompat.getDrawable(this, R.drawable.bg_booking_status_done)
            }
            BookingStatusEnum.isOnGoing(booking.status) -> {
                tvBookingStatus.setTextColor(ContextCompat.getColor(this, R.color.colorSecondary))
                tvBookingStatus.background = ContextCompat.getDrawable(this, R.drawable.bg_booking_status_on_going)
            }
        }

        tvBookingStatus.text = booking.statusTransaction
        

        val productPrice = booking.bookingDetail?.productPrice ?: 0
        val paidPrice = booking.paidPrice ?: 0

        tvProductPrice.text = Utils.formatMoney(productPrice, "Rp - ", true)
        tvProductPrice2.text = Utils.formatMoney(productPrice, "Rp - ", true)
        tvDiscountAmount.text = " - " + Utils.formatMoney(abs(productPrice - paidPrice),"Rp. 0 ", true)
        tvTotalPrice.text = Utils.formatMoney(booking.paidPrice, "Rp - ", true)

        val firstPay = booking.paymentAmount ?: 0
        if(PaymentTypeEnum.getByTypeId(booking.paymentMethod) == PaymentTypeEnum.DOWN_PAYMENT && firstPay > 0) {
            tvBookingDpPaid.text = Utils.formatMoney(firstPay)
        } else {
            tvBookingDpPaid.text = Utils.formatMoney(0)
        }

        val remainingBill = booking.remainingBills ?: 0
        val paymentAmount = booking.paymentAmount ?: 0

        val isContainerConfirmPaymentVisible = if(BookingStatusEnum.isOnGoing(booking.status)) {
            if(PaymentTypeEnum.DOWN_PAYMENT.typeId == booking.paymentMethod) {
                remainingBill > 0
            } else paymentAmount <= 0
        } else {
            false
        }

        containerConfirmPayment.visibility = if(isContainerConfirmPaymentVisible) View.VISIBLE else View.GONE

        btnAction.visibility = if((BookingStatusEnum.isCompleted(booking.status) && booking.ratingId.isNullOrBlank())
                || BookingStatusEnum.isOnGoing(booking.status)) View.VISIBLE else View.GONE

        val actionText = if(BookingStatusEnum.isOnGoing(booking.status)) {
            getString(R.string.btn_fitting_size)
        } else if(BookingStatusEnum.isCompleted(booking.status) && booking.ratingId.isNullOrBlank()) {
            getString(R.string.btn_review_booking)
        } else { "" }
        btnAction.text = actionText
    }

    override fun navigateToConfirmPayment(booking: Booking) {
        Intent(this, ConfirmPaymentActivity::class.java).apply {
            putExtra("booking", booking)
            startActivityForResult(this, ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT)
        }
    }

    override fun navigateToReviewBooking(booking: Booking) {
        Intent(this, ReviewBookingActivity::class.java).apply {
            putExtra("booking", booking)
            startActivityForResult(this, ReviewBookingActivity.REQ_REVIEW_BOOKING)
        }
    }

    override fun navigateToFitting(transactionId: String?, fittingId: String?) {
        Intent(this, FittingSizeActivity::class.java).apply {
            putExtra("transaction_id", transactionId)
            putExtra("fitting_id", fittingId)

            startActivityForResult(this, FittingSizeActivity.REQ_EDIT_FITTING)
        }
    }

    override fun setResultBookingChanged(booking: Booking, finish: Boolean) {
        if(resultIntent == null) {
            resultIntent = Intent()
        }

        resultIntent?.putExtra("booking", booking)?.apply {
            intent = this

            setResult(RES_BOOKING_DETAIL_CHANGED, this)
            if(finish) finish()
        }
    }

    override fun getTransactionId(): String? = intent.getParcelableExtra("transaction_id")
    override fun getBookingData(): Booking? = intent.getParcelableExtra("booking")

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pay -> presenter.onBtnPayClicked()
            R.id.btn_action -> presenter.onBtnActionClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == FittingSizeActivity.REQ_EDIT_FITTING) {
            if(resultCode == FittingSizeActivity.RES_FITTING_SAVED) {
                presenter.onFittingSaved(data?.getStringExtra("transaction_id"), data?.getStringExtra("fitting_id"))
            }
        } else if(requestCode == ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT) {
            if(resultCode == ConfirmPaymentActivity.RES_CONFIRM_PAYMENT_SUCCESS) {
                presenter.onPaymentConfirmationSaved(data?.getParcelableExtra("booking"))
            }
        } else if(requestCode == ReviewBookingActivity.REQ_REVIEW_BOOKING) {
            if(resultCode == ReviewBookingActivity.RES_BOOKING_REVIEWED) {
                presenter.onBookingReviewed(data?.getStringExtra("transaction_id"), data?.getStringExtra("rating_id"))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
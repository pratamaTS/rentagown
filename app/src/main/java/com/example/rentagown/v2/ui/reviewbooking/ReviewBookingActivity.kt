package com.example.rentagown.v2.ui.reviewbooking

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.util.Utils

class ReviewBookingActivity : BaseRAGActivity<ReviewBookingContract.Presenter>(), ReviewBookingContract.View,
        View.OnClickListener {

    companion object {
        const val REQ_REVIEW_BOOKING = 500
        const val RES_BOOKING_REVIEWED = 501
    }

    override val layoutId: Int = R.layout.activity_review_booking_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: ReviewBookingContract.Presenter

    private lateinit var ivProductImage: ImageView

    private lateinit var tvProductName: TextView
    private lateinit var tvProductCategoryName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var rbBooking: RatingBar
    private lateinit var etReviewComment: TextView
    private lateinit var tVReviewCommentCharactersLeft: TextView

    private lateinit var btnSubmit: Button

    private lateinit var reviewCommentTextWatcher: TextWatcher

    override fun init() {
        super.init()

        presenter = ReviewBookingPresenter(RepositoryLocator
                .getInstance(RemoteRepositoryLocator
                        .getInstance(RAGApi
                                .apiService(this)))
                .bookingRepository)

    }

    override fun setupWidgets() {
        super.setupWidgets()

        ivProductImage = findViewById(R.id.iv_product_image)

        tvProductName = findViewById(R.id.tv_product_name)
        tvProductCategoryName = findViewById(R.id.tv_product_category_name)
        tvProductPrice = findViewById(R.id.tv_product_price)
        rbBooking = findViewById(R.id.rb_booking)
        etReviewComment = findViewById(R.id.et_review_comment)
        tVReviewCommentCharactersLeft = findViewById(R.id.tv_review_comment_characters_left)

        btnSubmit = findViewById(R.id.btn_submit_review)

        btnSubmit.setOnClickListener(this)

        reviewCommentTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var maxLength: Int = 0

                etReviewComment.filters.forEach { f ->
                    if(f is InputFilter.LengthFilter) {
                        maxLength = f.max
                    }
                }

                if(s == null) {
                    tVReviewCommentCharactersLeft.text = "$maxLength ${getString(R.string.lbl_characters_left)}"
                } else {
                    tVReviewCommentCharactersLeft.text = "${ maxLength - (s?.length ?: 0) } ${getString(R.string.lbl_characters_left)}"
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        }
        etReviewComment.addTextChangedListener(reviewCommentTextWatcher)

    }

    override fun getBookingData(): Booking? = intent.getParcelableExtra("booking")

    override fun getReviewStart(): Float {
        return rbBooking.rating
    }

    override fun getReviewComment(): String {
        return etReviewComment.text.toString().trim()
    }

    override fun setDataBookingToView(booking: Booking) {
        Glide.with(this)
                .load(BuildConfig.BASE_PHOTO_URL + booking.photoPath)
                .listener(Utils.getGlideException())
                .centerCrop()
                .error(R.color.colorGray)
                .into(ivProductImage)

        tvProductName.text = booking.productName
        tvProductPrice.text = Utils.formatMoney(booking.paidPrice)
        tvProductCategoryName.text = booking.bookingDetail?.productCategoryName

    }

    override fun showMsgSuccessReviewBooking() {
        showMessage(getString(R.string.msg_success_review_booking))
    }

    override fun setResultBookingReviewed(transactionId: String?, ratingId: String?) {
        Intent().apply {
            putExtra("transaction_id", transactionId)
            putExtra("rating_id", ratingId)

            setResult(RES_BOOKING_REVIEWED, this)
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_submit_review -> presenter.onBtnSubmitReviewClicked()

        }
    }

}
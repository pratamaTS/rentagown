package com.example.rentagown.v2.ui.bookingcart

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.ui.booking.BookingActivity
import com.example.rentagown.v2.util.Utils

class BookingCartActivity : BaseRAGActivity<BookingCartContract.Presenter>(), BookingCartContract.View,
        View.OnClickListener {

    companion object {
        const val REQ_CREATE_BOOKING = 746
        const val RES_BOOKING_CREATED = 745
    }

    override val layoutId: Int = R.layout.activity_booking_cart_v2

    override lateinit var presenter: BookingCartContract.Presenter
    override var btnBackId: Int = R.id.btn_back

    private lateinit var ivProductImage: ImageView

    private lateinit var tvProductName: TextView
    private lateinit var tvBookingStartEndDate: TextView
    private lateinit var tvProductCategory: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvSpecialTreatment: TextView
    private lateinit var btnDelete: ImageButton

    private lateinit var tvCartInfo: TextView
    private lateinit var btnWhatsapp: ImageButton
    private lateinit var btnPay: Button


    override fun init() {
        super.init()


        presenter = BookingCartPresenter()
    }

    override fun setupWidgets() {
        super.setupWidgets()

        ivProductImage = findViewById(R.id.iv_product_image)

        tvProductName = findViewById(R.id.tv_product_name)
        tvBookingStartEndDate = findViewById(R.id.tv_booking_start_end_date)
        tvProductCategory = findViewById(R.id.tv_product_category)
        tvProductPrice = findViewById(R.id.tv_product_price)
        tvSpecialTreatment = findViewById(R.id.tv_special_treatment)
        btnDelete = findViewById(R.id.btn_delete)

        tvCartInfo = findViewById(R.id.tv_cart_info)
        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnPay = findViewById(R.id.btn_pay)

        btnDelete.setOnClickListener(this)
        btnWhatsapp.setOnClickListener(this)
        btnPay.setOnClickListener(this)

    }

    override fun setDataToView(createBooking: ReqCreateBooking, product: Product) {
        Glide.with(this)
                .load(BuildConfig.BASE_PHOTO_URL + product.photoPath)
                .listener(Utils.getGlideException())
                .centerCrop()
                .error(R.color.colorGray)
                .into(ivProductImage)

        tvProductName.text = product.productName
        tvBookingStartEndDate.text = Utils.formatMyBookingStartEndDate(createBooking.startDate, createBooking.endDate, Utils.DATE_FORMAT_CREATE_BOOKING)
        tvProductCategory.text = product.productCategoryName
        tvProductPrice.text = Utils.formatMoney(product.finalPrice)
        tvSpecialTreatment.visibility = if(createBooking.oneDayService == 1) View.VISIBLE else View.INVISIBLE

        val endBooking = Utils.parseDate(createBooking.endDate, Utils.DATE_FORMAT_CREATE_BOOKING)
        if(endBooking != null) {
            val endReturn = endBooking.plusDays(7)
            tvCartInfo.visibility = View.VISIBLE
            val newText = tvCartInfo.text.toString().replace("#returnDeadLine", Utils.formatDateBooking(endReturn))
            tvCartInfo.text = newText
        } else {
            tvCartInfo.visibility = View.INVISIBLE
        }

    }

    override fun getReqCreateBookingData(): ReqCreateBooking? = intent.getParcelableExtra("req_create_booking")

    override fun getProductData(): Product? = intent.getParcelableExtra("product")

    override fun navigateToWhatsapp(phoneNumber: String) {
        val url = "https://api.whatsapp.com/send/?phone=$phoneNumber"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    override fun navigateToBooking(createBooking: ReqCreateBooking, product: Product) {
        Intent(this, BookingActivity::class.java).apply {
            putExtra("req_create_booking", createBooking)
            putExtra("product", product)

            startActivityForResult(this, BookingActivity.REQ_CREATE_BOOKING)
        }
    }

    override fun showMsgBookingNotFound() {
        showMessage(getString(R.string.err_booking_not_found))
    }

    override fun setResultBookingCreated() {
        setResult(RES_BOOKING_CREATED)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_delete -> presenter.onBtnDeleteClicked()
            R.id.btn_whatsapp -> presenter.onBtnWhatsappClicked()
            R.id.btn_pay -> presenter.onBtnPayClicked()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == BookingActivity.REQ_CREATE_BOOKING) {
            if(resultCode == BookingActivity.RES_BOOKING_CREATED) {
                presenter.onBookingCreated()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
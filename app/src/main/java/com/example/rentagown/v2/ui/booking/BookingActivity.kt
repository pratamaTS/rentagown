package com.example.rentagown.v2.ui.booking

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.*
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bookingsuccess.BookingSuccessActivity
import com.example.rentagown.v2.ui.bookingsummary.BookingSummaryActivity
import com.example.rentagown.v2.ui.chooseaddress.ChooseAddressActivity
import com.example.rentagown.v2.ui.choosebank.ChooseBankActivity
import com.example.rentagown.v2.ui.choosepaymenttype.ChoosePaymentTypeDialog
import com.example.rentagown.v2.ui.confirmpayment.ConfirmPaymentActivity
import com.example.rentagown.v2.ui.productdetail.ProductDetailPresenter
import com.example.rentagown.v2.util.Utils

class BookingActivity : BaseRAGActivity<BookingContract.Presenter>(), BookingContract.View,
        View.OnClickListener, ChoosePaymentTypeDialog.Listener {

    companion object {
        const val REQ_CREATE_BOOKING = 512
        const val RES_BOOKING_CREATED = 513
    }

    override val layoutId: Int = R.layout.activity_booking_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: BookingContract.Presenter

    private lateinit var btnAddAddress: Button
    private lateinit var btnChangeAddress: Button

    private lateinit var btnAddPaymentMethod: Button
    private lateinit var btnChangePaymentMethod: CardView

    private lateinit var btnChoosePaymentType: CardView

    private lateinit var containerAddressDetail: View
    private lateinit var tvAddressName: TextView
    private lateinit var tvDefaultAddress: TextView
    private lateinit var tvReceiverName: TextView
    private lateinit var tvReceiverPhoneNumber: TextView
    private lateinit var tvAddressDetail: TextView

    private lateinit var ivPaymentMethodLogo: ImageView
    private lateinit var tvPaymentMethod: TextView
    private lateinit var tvAccountDestName: TextView
    private lateinit var tvAccountDestNumber: TextView

    private lateinit var tvPaymentType: TextView
    private lateinit var tvLblDefaultMethod: TextView

    private lateinit var tvLblSelectedPaymentType: TextView
    private lateinit var tvSelectedPaymentTypePrice: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductFinalPrice: TextView
    private lateinit var tvTotalPayment: TextView

    private lateinit var imDepositBook: ImageView
    private lateinit var imFullBook: ImageView

    private lateinit var containerSpecialTreatment: View

    private lateinit var btnWhatsapp: ImageButton
    private lateinit var btnPay: Button

    private lateinit var choosePaymentTypeDialog: ChoosePaymentTypeDialog


    private var selectedAddress: Address? = null
    private var selectedBank: Bank? = null
    private var selectedPaymentType: PaymentTypeEnum? = null
    private var remainsBill: Long? = null


    override fun init() {
        super.init()

        presenter = BookingPresenter(RepositoryLocator.getInstance(RemoteRepositoryLocator
                .getInstance(RAGApi.apiService(this)))
                .bookingRepository)
    }

    override fun setupWidgets() {
        super.setupWidgets()

        btnAddAddress = findViewById(R.id.btn_add_address)
        btnChangeAddress = findViewById(R.id.btn_change_address)

        btnAddPaymentMethod = findViewById(R.id.btn_add_payment_method)
        btnChangePaymentMethod = findViewById(R.id.btn_change_payment_method)

        btnChoosePaymentType = findViewById(R.id.btn_choose_payment_type)

        imDepositBook = findViewById(R.id.iv_payment_type)
        imFullBook = findViewById(R.id.iv_payment_type2)

        containerAddressDetail = findViewById(R.id.container_address_detail)
        tvAddressName = findViewById(R.id.tv_address_name)
        tvDefaultAddress = findViewById(R.id.tv_label_default_address_booking)
        tvReceiverName = findViewById(R.id.tv_receiver_name)
        tvReceiverPhoneNumber = findViewById(R.id.tv_receiver_phone_number)
        tvAddressDetail = findViewById(R.id.tv_address_detail)

        ivPaymentMethodLogo = findViewById(R.id.iv_payment_method_logo)
        tvPaymentMethod = findViewById(R.id.tv_payment_method)
        tvAccountDestName = findViewById(R.id.tv_account_dest_name)
        tvAccountDestNumber = findViewById(R.id.tv_account_dest_number)

        tvPaymentType = findViewById(R.id.tv_payment_type)
        tvLblDefaultMethod = findViewById(R.id.tv_lbl_default_method)

        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnPay = findViewById(R.id.btn_pay)

        tvLblSelectedPaymentType = findViewById(R.id.tv_lbl_selected_payment_type)
        tvSelectedPaymentTypePrice = findViewById(R.id.tv_selected_payment_type_price)
        tvProductName = findViewById(R.id.tv_product_name)
        tvProductFinalPrice = findViewById(R.id.tv_product_final_price)
        tvTotalPayment = findViewById(R.id.tv_total_payment)

        containerSpecialTreatment = findViewById(R.id.container_special_treatment)

        if(!this::choosePaymentTypeDialog.isInitialized) {
            choosePaymentTypeDialog = ChoosePaymentTypeDialog()
        }

        btnAddAddress.setOnClickListener(this)
        btnChangeAddress.setOnClickListener(this)
        btnAddPaymentMethod.setOnClickListener(this)
        btnChangePaymentMethod.setOnClickListener(this)
        btnChoosePaymentType.setOnClickListener(this)
        btnWhatsapp.setOnClickListener(this)
        btnPay.setOnClickListener(this)

    }

    override fun onPause() {
        if(choosePaymentTypeDialog.isVisible) {
            choosePaymentTypeDialog.dismiss()
        }
        super.onPause()
    }

    private fun getReqCreateBooking(): ReqCreateBooking? = intent.getParcelableExtra("req_create_booking")

    override fun navigateToChangeAddress(selectedAddress: Address?) {
        Intent(this, ChooseAddressActivity::class.java).apply {
            putExtra("selected_address_id", selectedAddress?.addressId)

            startActivityForResult(this, ChooseAddressActivity.REQ_CHOOSE_ADDRESS)
        }
    }

    override fun navigateToChangeBank(selectedBank: Bank?) {
        Intent(this, ChooseBankActivity::class.java).apply {
            putExtra("selected_bank_id", selectedBank?.bankId)

            startActivityForResult(this, ChooseBankActivity.REQ_CHOOSE_BANK)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setAddressDataToView(address: Address) {
        this.selectedAddress = address
        Log.d("address", address.toString())
        if(address.isDefault == 1) {
            btnChangeAddress.visibility = View.VISIBLE
            btnAddAddress.visibility = View.GONE
            containerAddressDetail.visibility = View.VISIBLE

            tvAddressName.text = address.addressLabel
            tvDefaultAddress.visibility = View.VISIBLE
            tvReceiverName.text = address.receiverName
            tvReceiverPhoneNumber.text = "(${address.receiverPhoneNumber})"
            tvAddressDetail.text = address.address
        }else if(address.addressId != "" && address.isDefault == 0) {
            btnChangeAddress.visibility = View.VISIBLE
            btnAddAddress.visibility = View.GONE
            containerAddressDetail.visibility = View.VISIBLE

            tvAddressName.text = address.addressLabel
            tvDefaultAddress.visibility = View.INVISIBLE
            tvReceiverName.text = address.receiverName
            tvReceiverPhoneNumber.text = "(${address.receiverPhoneNumber})"
            tvAddressDetail.text = address.address
        }
    }

    override fun removeAddressDataFromView() {
        this.selectedAddress = null

        btnChangeAddress.visibility = View.GONE
        btnAddAddress.visibility = View.VISIBLE
        containerAddressDetail.visibility = View.GONE


        tvAddressName.text = null
        tvReceiverName.text = null
        tvReceiverPhoneNumber.text = null
        tvAddressDetail.text = null
    }

    override fun getSelectedAddressData(): Address? {
        return selectedAddress
    }

    override fun setBankDataToView(bank: Bank) {
        this.selectedBank = bank

        btnAddPaymentMethod.visibility = View.GONE
        btnChangePaymentMethod.visibility = View.VISIBLE

        Glide.with(this)
            .load(BuildConfig.BASE_PHOTO_URL + bank.photoPath)
            .listener(Utils.getGlideException())
            .fitCenter()
            .error(R.color.colorGray)
            .into(ivPaymentMethodLogo)

        tvPaymentMethod.text = bank.bankName
        tvAccountDestName.text = bank.accountName
        tvAccountDestNumber.text = bank.accountNumber
    }

    override fun getSelectedProduct(): Product? = intent.getParcelableExtra("product")

    override fun setResultBookingCreated() {
        setResult(RES_BOOKING_CREATED)
    }

    override fun navigateToBookingSummary(booking: Booking) {
        Intent(this, BookingSummaryActivity::class.java).apply {
            putExtra("booking", booking)
            startActivity(this)
            finish()
        }
    }

    override fun showMsgAddressNotSelected() {
        showMessage(getString(R.string.err_not_selected_any_address))
    }

    override fun showMsgPaymentTypeNotSelected() {
        showMessage(getString(R.string.err_not_selected_any_payment_type))
    }

    override fun showMsgPaymentMethodNotSelected() {
        showMessage(getString(R.string.err_not_selected_any_payment_method))
    }

    override fun showMsgProductNotFound() {
        showMessage(getString(R.string.err_product_not_found))
    }

    override fun showMsgBookingDataNotFound() {
        showMessage(getString(R.string.err_booking_not_found))
    }

    override fun removeBankDataFromView() {
        this.selectedBank = null

        btnAddPaymentMethod.visibility = View.VISIBLE
        btnChangePaymentMethod.visibility = View.GONE

        ivPaymentMethodLogo.setImageDrawable(null)
        tvPaymentMethod.text = null
        tvAccountDestName.text = null
        tvAccountDestNumber.text = null
    }

    override fun getSelectedBankData(): Bank? {
        return selectedBank
    }

    override fun navigateToWhatsapp(phoneNumber: String) {
        val url = "https://api.whatsapp.com/send/?phone=$phoneNumber"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    override fun getReqCreateBookingData(): ReqCreateBooking? = intent.getParcelableExtra("req_create_booking")

    override fun navigateToChoosePaymentType(paymentType: PaymentTypeEnum?) {
        val bundle = Bundle()
        bundle.putInt("selected_payment_type", paymentType?.ordinal ?: -1)

        if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            choosePaymentTypeDialog.arguments = bundle
            choosePaymentTypeDialog.show(supportFragmentManager, ChoosePaymentTypeDialog.TAG, this)
        }
    }

    override fun getSelectedPaymentType(): PaymentTypeEnum? {
        return selectedPaymentType
    }

    @SuppressLint("SetTextI18n")
    override fun setPaymentTypeDataToView(paymentType: PaymentTypeEnum, reqCreateBooking: ReqCreateBooking?, product: Product?) {
        this.selectedPaymentType = paymentType

        if(paymentType.isFullPayment){
            imDepositBook.visibility = View.GONE
            imFullBook.visibility = View.VISIBLE
        }else{
            imDepositBook.visibility = View.VISIBLE
            imFullBook.visibility = View.GONE
        }

        tvPaymentType.text = paymentType.typeName
        tvLblDefaultMethod.visibility = if(paymentType == PaymentTypeEnum.getDefaultPaymentType()) View.VISIBLE else View.GONE

        // set moneyvalue
        tvLblSelectedPaymentType.visibility = View.VISIBLE
        tvSelectedPaymentTypePrice.visibility = View.VISIBLE


        if(!paymentType.isFullPayment){
            remainsBill = product?.finalPrice?.minus(paymentType.dpValue)
            tvLblSelectedPaymentType.text = "Remaining Bills"
        }else{
            tvLblSelectedPaymentType.text = paymentType.typeName
        }

        tvSelectedPaymentTypePrice.text = if(paymentType.isFullPayment) Utils.formatMoney(product?.finalPrice) else Utils.formatMoney(remainsBill)

        tvProductName.text = product?.productName
        tvProductFinalPrice.text = " - ${Utils.formatMoney(product?.finalPrice)}"

        tvTotalPayment.text = if(paymentType.isFullPayment) Utils.formatMoney(product?.finalPrice) else Utils.formatMoney(paymentType.dpValue)

        containerSpecialTreatment.visibility = if(reqCreateBooking?.oneDayService == 1) View.VISIBLE else View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    override fun removePaymentTypeDataFromView(reqCreateBooking: ReqCreateBooking?, product: Product?) {
        this.selectedPaymentType = null

        tvLblSelectedPaymentType.visibility = View.INVISIBLE
        tvSelectedPaymentTypePrice.visibility = View.INVISIBLE

        tvSelectedPaymentTypePrice.text = getString(R.string.lbl_no_text)
        tvProductFinalPrice.text = getString(R.string.lbl_no_text)
        tvTotalPayment.text = getString(R.string.lbl_no_text)

        tvProductName.text = product?.productName
        tvProductFinalPrice.text = " - ${Utils.formatMoney(product?.finalPrice)}"

        containerSpecialTreatment.visibility = if(reqCreateBooking?.oneDayService == 1) View.VISIBLE else View.INVISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_address -> presenter.onBtnAddAddressClicked()
            R.id.btn_change_address -> presenter.onBtnChangeAddressClicked()
            R.id.btn_add_payment_method -> presenter.onBtnAddBankClicked()
            R.id.btn_change_payment_method -> presenter.onBtnChangeBankClicked()
            R.id.btn_choose_payment_type -> presenter.onBtnChoosePaymentTypeClicked()
            R.id.btn_whatsapp -> presenter.onBtnWhatsappClicked()
            R.id.btn_pay -> {
                val msg1 = "Please make sure you fill a correct data"
                val msg2 = "Are you sure you want to continue?"
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmation")
                        .setMessage(msg1 +"\n\n"+ msg2)
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, id ->
                            presenter.onBtnPayClicked()
                        }
                        .setNegativeButton("No") { dialog, id ->
                            // Dismiss the dialog
                            dialog.dismiss()
                        }
                val alert = builder.create()
                alert.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ChooseAddressActivity.REQ_CHOOSE_ADDRESS) {
            if(resultCode == RESULT_OK) {
                val selectedAddress = data?.getParcelableExtra<Address>("selected_address")
                val editedAddress = data?.getParcelableExtra<Address>("edited_address")
                val deletedAddress = data?.getParcelableExtra<Address>("deleted_address")
                val default: Boolean? = data?.getBooleanExtra("default", false)

                when {
                    selectedAddress != null -> {
                        presenter.onAddressSelected(selectedAddress, default!!)
                    }
                    editedAddress != null -> {
                        presenter.onAddressEdited(editedAddress)
                    }
                    deletedAddress != null -> {
                        presenter.onAddressDeleted(deletedAddress)
                    }
                }

            }
        } else if(requestCode == ChooseBankActivity.REQ_CHOOSE_BANK) {
            if(resultCode == ChooseBankActivity.RES_BANK_CHOSEN) {
                presenter.onBankSelected(data?.getParcelableExtra("selected_bank"))
            }
        } else if(requestCode == ConfirmPaymentActivity.REQ_CONFIRM_PAYMENT) {
            if(resultCode == ConfirmPaymentActivity.RES_CONFIRM_PAYMENT_SUCCESS) {
                data?.getParcelableExtra<Booking>("booking")?.apply {
                    setResultBookingCreated()

                    val intent = Intent(this@BookingActivity, BookingSuccessActivity::class.java)
                    startActivity(intent)

                    finish()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSelectedPaymentType(selectedPaymentType: PaymentTypeEnum) {
        presenter.onSelectedPaymentType(selectedPaymentType)
    }

}
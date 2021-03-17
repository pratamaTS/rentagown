package com.example.rentagown.v2.ui.confirmpayment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.MasterBank
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bookingsuccess.BookingSuccessActivity
import com.skydoves.powerspinner.IconSpinnerAdapter
import com.skydoves.powerspinner.IconSpinnerItem
import com.skydoves.powerspinner.PowerSpinnerView


class ConfirmPaymentActivity : BaseRAGActivity<ConfirmPaymentContract.Presenter>(), ConfirmPaymentContract.View,
        View.OnClickListener {

    companion object {

        const val REQ_CONFIRM_PAYMENT = 975
        const val RES_CONFIRM_PAYMENT_SUCCESS = 976

    }

    override val layoutId: Int = R.layout.activity_confirm_payment_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: ConfirmPaymentContract.Presenter

    private lateinit var tvConfirmationAmount: TextView
    private lateinit var spBankName: PowerSpinnerView
//    private lateinit var etBankName: TextView
    private lateinit var etAccountNumber: TextView
    private lateinit var etAccountName: TextView
    private lateinit var etPaymentAmount: TextView

    private lateinit var btnConfirm: Button
    var idBank: String? = null

    override fun init() {
        super.init()

        presenter = ConfirmPaymentPresenter(
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

        tvConfirmationAmount = findViewById(R.id.tv_confirmation_amount)
        spBankName = findViewById(R.id.spinner_bank_name)
//        etBankName = findViewById(R.id.et_bank_name)
        etAccountNumber = findViewById(R.id.et_account_number)
        etAccountName = findViewById(R.id.et_account_name)
        etPaymentAmount = findViewById(R.id.et_payment_amount)

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

    override fun getBookingData(): Booking? = intent.getParcelableExtra("booking")

    override fun setSpinnerBankItem(bank: List<MasterBank>) {
        val listItem = arrayListOf<IconSpinnerItem>()

        for(item in bank){
            val path: String = "http://absdigital.id:55000" + item.pathImage

            listItem.add(
                IconSpinnerItem(
                    text = item.displayName.toString(),
                    icon = Drawable.createFromPath(path)
                )
            )
        }

        spBankName.apply {
            setSpinnerAdapter(IconSpinnerAdapter(this))

            setItems(listItem)
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(context, 1)
            selectItemByIndex(0) // select an item initially.
            lifecycleOwner = this@ConfirmPaymentActivity
        }

        spBankName.setOnSpinnerItemSelectedListener<IconSpinnerItem> { oldIndex, oldItem, newIndex, newItem ->
            val text = newItem.text
            idBank = bank.find { it.displayName == newItem.text }?.idMstBank
            Log.d("id bank", idBank.toString())
        }
    }

    override fun setPaymentValue(paymentValueStr: String?) {
        tvConfirmationAmount.text = paymentValueStr
    }

    override fun getInvoice(): String {
        return getBookingData()?.invoice.toString()
    }

    override fun getBankID(): String {
        return idBank.toString()
    }

    override fun getAccountNumber(): String {
        return etAccountNumber.text.toString().trim()
    }

    override fun getAccountName(): String {
        return etAccountName.text.toString().trim()
    }

    override fun getPaymentAmount(): String {
        return etPaymentAmount.text.toString().trim()
    }

    override fun setResultPaymentSuccess(booking: Booking?) {
        Intent().apply {
            putExtra("booking", booking)
            setResult(RES_CONFIRM_PAYMENT_SUCCESS, this)
            finish()
        }
    }

    override fun showMsgBookingNotFound() {
        showMessage(getString(R.string.err_booking_not_found))
    }

    override fun showMsgBankEmpty() {
        showMessage(getString(R.string.err_source_bank_name_empty))
    }

    override fun showMsgAccountNumberEmpty() {
        showMessage(getString(R.string.err_source_acc_number_empty))
    }

    override fun showMsgAccountNameEmpty() {
        showMessage(getString(R.string.err_source_acc_name_empty))
    }

    override fun showMsgPaymentAmountEmpty() {
        showMessage(getString(R.string.err_payment_amount_empty))
    }

}
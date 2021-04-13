package com.example.rentagown.v2.ui.invoicedetail

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.Invoice
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.util.Utils
import okhttp3.internal.Util
import kotlin.math.abs

class InvoiceDetailActivity : BaseRAGActivity<InvoiceDetailContract.Presenter>(), InvoiceDetailContract.View {

    companion object {

        const val REQ_VIEW_INVOICE_DETAIL = 253
        const val RES_INVOICE_PAID = 252

    }


    override val layoutId: Int = R.layout.activity_invoice_detail_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: InvoiceDetailContract.Presenter

    private lateinit var tvInvoice: TextView
    private lateinit var tvPaymentDate: TextView
    private lateinit var tvPaymentDeadline: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvTotalPriceInvoice: TextView

    private lateinit var tvDiscountAmount: TextView
    private lateinit var tvLateChargeAmount: TextView
    private lateinit var tvDownPaymentAmount: TextView
    private lateinit var tvDestAccountNumber: TextView
    private lateinit var tvDestAccountName: TextView

    private lateinit var ivDestBankLogo: ImageView

    override fun init() {
        super.init()

        presenter = InvoiceDetailPresenter(
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

        tvInvoice = findViewById(R.id.tv_invoice)
        tvPaymentDate = findViewById(R.id.tv_payment_date)
        tvPaymentDeadline = findViewById(R.id.tv_payment_deadline)
        tvProductName = findViewById(R.id.tv_product_name)
        tvProductPrice = findViewById(R.id.tv_product_price)
        tvTotalPriceInvoice = findViewById(R.id.tv_total_price_invoice)

        tvDiscountAmount = findViewById(R.id.tv_discount_amount)
        tvLateChargeAmount = findViewById(R.id.tv_late_charge_amount)
        tvDownPaymentAmount = findViewById(R.id.tv_down_payment_amount)
        tvDestAccountNumber = findViewById(R.id.tv_dest_account_number)
        tvDestAccountName = findViewById(R.id.tv_dest_account_name)

        ivDestBankLogo = findViewById(R.id.iv_dest_bank_logo)

    }

    @SuppressLint("SetTextI18n")
    override fun setDataInvoiceToView(invoice: Invoice) {

        Glide.with(this)
                .load(BuildConfig.BASE_PHOTO_URL + invoice.booking?.bankPathPhoto)
                .listener(Utils.getGlideException())
                .fitCenter()
                .error(R.color.colorGray)
                .into(ivDestBankLogo)

        ivDestBankLogo.visibility = View.VISIBLE

        tvInvoice.text = invoice.invoice
        tvPaymentDate.text = Utils.formatDateTime(invoice.createdAt, Utils.DATE_TIME_FORMAT2)
        tvPaymentDeadline.text = Utils.formatDateTime(invoice.paymentDeadline, Utils.DATE_TIME_FORMAT, Utils.DATE_TIME_FORMAT_PAYMENT_DEADLINE2)
        tvProductName.text = invoice.booking?.productName
        tvProductPrice.text = Utils.formatMoney(invoice.invoiceAmount, "Rp. - ", true)
        tvTotalPriceInvoice.text = Utils.formatMoney(invoice.invoiceAmount, "Rp. - ", true)

        val productPrice = invoice.booking?.paidPrice ?: 0
        val paidPrice = invoice.booking?.paidPrice ?: 0

        val paymentType = PaymentTypeEnum.getByTypeId(invoice.booking?.paymentMethod)

        tvDiscountAmount.text =  " - "  + if(productPrice > 0) Utils.formatMoney(abs(productPrice - paidPrice),"Rp. 0 ", true)
                                        else Utils.formatMoney(0, "Rp. -", true)

        tvLateChargeAmount.text = " - " + Utils.formatMoney(0,"Rp. 0 ")
        tvDownPaymentAmount.text = if(paymentType == PaymentTypeEnum.DOWN_PAYMENT) Utils.formatMoney(invoice.booking?.downPayment,"Rp. - ", true) else Utils.formatMoney(0)
        tvDestAccountNumber.text = invoice.paymentSourceAccNumber
        tvDestAccountName.text = invoice.paymentSourceAccName

    }

    override fun getInvoiceId(): String? = intent.getStringExtra("invoiceId")

    override fun showMsgInvoiceNotFound() {
        showMessage(getString(R.string.err_invoice_not_found))
    }

}
package com.example.rentagown.v2.ui.myinvoices.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.Invoice
import com.example.rentagown.v2.util.Utils
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class MyInvoiceItem(model: Invoice) : ModelAbstractItem<Invoice, MyInvoiceItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_my_invoice_v2
    override val type: Int = R.id.item_my_invoice

    override var identifier: Long = if(model.transactionId.isNullOrBlank()) -1 else model.transactionId.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        holder.tvInvoice.text = model.invoice ?: "-"
        holder.tvInvoiceAmount.text = Utils.formatMoney(model.paidPrice, "Rp. -", true)
        holder.tvInvoicePaymentMethod.text = model.paymentMethod ?: "-"
        holder.tvInvoiceDate.text = Utils.formatDateTime(model.createdAt, Utils.DATE_TIME_FORMAT2)
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.tvInvoice.text = null
        holder.tvInvoiceAmount.text = null
        holder.tvInvoicePaymentMethod.text = null
        holder.tvInvoiceDate.text = null
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var tvInvoice: TextView = v.findViewById(R.id.tv_invoice)
        var tvInvoiceAmount: TextView = v.findViewById(R.id.tv_invoice_amount)
        var tvInvoicePaymentMethod: TextView = v.findViewById(R.id.tv_invoice_payment_method)
        var tvInvoiceDate: TextView = v.findViewById(R.id.tv_invoice_date)

    }

}
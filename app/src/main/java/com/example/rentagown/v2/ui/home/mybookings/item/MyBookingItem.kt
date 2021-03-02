package com.example.rentagown.v2.ui.home.mybookings.item

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.util.Utils
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class MyBookingItem(model: Booking) : ModelAbstractItem<Booking, MyBookingItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_my_booking_v2
    override val type: Int = R.id.item_my_booking

    override var identifier: Long = if(model.transactionId.isNullOrBlank()) -1 else model.transactionId.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        Glide.with(holder.itemView.context)
            .load(BuildConfig.BASE_PHOTO_URL + model.photoPath)
            .listener(Utils.getGlideException())
            .centerCrop()
            .error(R.color.colorGray)
            .into(holder.ivProductImage)

        holder.tvProductName.text = model.productName
        holder.tvProductPrice.text = Utils.formatMoney(model.paidPrice)

        val firstPay = model.paymentAmount ?: 0
        if(PaymentTypeEnum.getByTypeId(model.paymentMethod) == PaymentTypeEnum.DOWN_PAYMENT && firstPay > 0) {
            holder.tvDpPaid.text = Utils.formatMoney(firstPay)
        } else {
            holder.tvDpPaid.text = Utils.formatMoney(0)
        }

        holder.tvRemainingBill.text = Utils.formatMoney(model.remainingBills)
        holder.tvBookingStartEndDate.text = Utils.formatMyBookingStartEndDate(model.startDate, model.endDate)
        holder.tvBookingStatus.text = model.statusTransaction
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.ivProductImage.setImageDrawable(null)
        holder.tvProductName.text = null
        holder.tvProductPrice.text = null
        holder.tvDpPaid.text = null
        holder.tvRemainingBill.text = null
        holder.tvBookingStartEndDate.text = null
        holder.tvBookingStatus.text = null

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivProductImage: ImageView = view.findViewById(R.id.iv_product_image)
        var tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        var tvProductPrice: TextView = view.findViewById(R.id.tv_product_price)
        var tvDpPaid: TextView = view.findViewById(R.id.tv_dp_paid)
        var tvRemainingBill: TextView = view.findViewById(R.id.tv_remaining_bill)
        var tvBookingStartEndDate: TextView = view.findViewById(R.id.tv_booking_start_end_date)
        var tvBookingStatus: TextView = view.findViewById(R.id.tv_booking_status)

        var btnFittingSize: Button = view.findViewById(R.id.btn_fitting_size)

    }


}
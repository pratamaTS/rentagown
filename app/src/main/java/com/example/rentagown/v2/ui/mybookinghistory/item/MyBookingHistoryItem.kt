package com.example.rentagown.v2.ui.mybookinghistory.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.data.enums.BookingStatusEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.util.Utils
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class MyBookingHistoryItem(model: Booking) : ModelAbstractItem<Booking,MyBookingHistoryItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_my_booking_history_v2
    override val type: Int = R.id.item_my_booking_history

    override var identifier: Long = if(model.transactionId.isNullOrBlank()) -1 else model.transactionId.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        holder.ivProductImage.setImageDrawable(null)

        Glide.with(holder.itemView.context)
                .load(BuildConfig.BASE_PHOTO_URL + model.photoPath)
                .listener(Utils.getGlideException())
                .fitCenter()
                .error(R.color.colorGray)
                .into(holder.ivProductImage)

        if(BookingStatusEnum.isCancelled(model.status)) {
            holder.tvStatusHistory.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.colorWhite))
            holder.tvStatusHistory.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_status_history_cancel)
        } else {
            holder.tvStatusHistory.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.colorDarkGreen))
            holder.tvStatusHistory.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_status_history_done)
        }

        holder.tvBookingStartEndDate.text = Utils.formatMyBookingStartEndDate(model.startDate, model.endDate)
        holder.tvStatusHistory.text = model.statusTransaction
        holder.tvProductName.text = model.productName
        holder.tvProductCategory.text = BookingStatusEnum.getByStatusId(model.status)?.statusName
        holder.tvTotalPrice.text = Utils.formatMoney(model.paidPrice)
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.ivProductImage.setImageDrawable(null)

        holder.tvBookingStartEndDate.text = null
        holder.tvStatusHistory.text = null
        holder.tvProductName.text = null
        holder.tvProductCategory.text = null
        holder.tvTotalPrice.text = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivProductImage: ImageView = view.findViewById(R.id.iv_product_image)

        var tvBookingStartEndDate: TextView = view.findViewById(R.id.tv_booking_start_end_date)
        var tvStatusHistory: TextView = view.findViewById(R.id.tv_status_history)
        var tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        var tvProductCategory: TextView = view.findViewById(R.id.tv_product_category)
        var tvTotalPrice: TextView = view.findViewById(R.id.tv_total_price)

    }



}
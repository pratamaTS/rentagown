package com.example.rentagown.v2.ui.unvailabledates.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.UnavailableDate
import com.example.rentagown.v2.util.Utils
import com.mikepenz.fastadapter.items.ModelAbstractItem
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UnavailableDateItem(model: UnavailableDate): ModelAbstractItem<UnavailableDate, UnavailableDateItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_unavailable_date_v2
    override val type: Int = R.id.item_unavailable_date

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Utils.LOCALE)
        val outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy", Utils.LOCALE)

        val startDt: LocalDate? = try { LocalDate.parse(model.startDate, inputFormat) } catch (e: Exception) { null }
        val endDt: LocalDate? = try { LocalDate.parse(model.endDate, inputFormat) } catch (e: Exception) { null }

        holder.lineDate.visibility = if(startDt != null && endDt != null) View.VISIBLE else View.GONE
        holder.tvEndDateNotAvailable.visibility = if(endDt != null) View.VISIBLE else View.GONE

        holder.tvStartDateNotAvailable.text = if(startDt != null) outputFormat.format(startDt) else null
        holder.tvEndDateNotAvailable.text = if(endDt != null) outputFormat.format(endDt) else null

    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.lineDate.visibility = View.VISIBLE
        holder.tvEndDateNotAvailable.visibility = View.VISIBLE

        holder.tvStartDateNotAvailable.text = null
        holder.tvEndDateNotAvailable.text = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvStartDateNotAvailable: TextView = view.findViewById(R.id.tv_start_date_not_available)
        var tvEndDateNotAvailable: TextView = view.findViewById(R.id.tv_end_date_not_available)
        var lineDate: View = view.findViewById(R.id.line_date)

    }

}
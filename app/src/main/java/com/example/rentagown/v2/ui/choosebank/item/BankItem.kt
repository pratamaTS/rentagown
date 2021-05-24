package com.example.rentagown.v2.ui.choosebank.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentagown.BuildConfig
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.util.Utils
import com.example.rentagown.v2.util.to64BitHash
import com.mikepenz.fastadapter.items.ModelAbstractItem

class BankItem(model: Bank, private val selectedBankId: String? = null) : ModelAbstractItem<Bank, BankItem.ViewHolder>(model) {

    override val layoutRes: Int = R.layout.item_bank_v2
    override val type: Int = R.id.item_bank

    override var identifier = if(model.bankId.isNullOrBlank()) -1 else model.bankId.to64BitHash()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        if(model.isDefault == 1) {
            holder.tvDefaultBank.visibility = View.VISIBLE
        }else{
            holder.tvDefaultBank.visibility = View.INVISIBLE
        }

        Glide.with(holder.itemView.context)
            .load(BuildConfig.BASE_PHOTO_URL + model.photoPath)
            .listener(Utils.getGlideException())
            .fitCenter()
            .error(R.color.colorGray)
            .into(holder.ivBankLogo)

        holder.tvMethodName.text = model.bankName
        holder.tvAccountName.text = model.accountName
        holder.tvAccountNumber.text = model.accountNumber
        holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
            if(!model.bankId.isNullOrBlank() && model.bankId == selectedBankId) R.color.selectedYellow else R.color.colorWhite))
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.tvMethodName.text = null
        holder.tvAccountName.text = null
        holder.tvAccountNumber.text = null
        holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorWhite))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var container: View = view.findViewById(R.id.container)
        var ivBankLogo: ImageView = view.findViewById(R.id.iv_bank_logo)
        var tvMethodName: TextView = view.findViewById(R.id.tv_method_name)
        var tvDefaultBank: TextView = view.findViewById(R.id.tv_label_default_bank)
        var tvAccountName: TextView = view.findViewById(R.id.tv_account_name)
        var tvAccountNumber: TextView = view.findViewById(R.id.tv_account_number)

    }



}
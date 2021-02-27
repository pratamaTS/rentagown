package com.example.rentagown.v2.ui.chooseaddress.item

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.util.to64BitHash
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.items.ModelAbstractItem

class AddressItem(model: Address, private val selectedAddressId: String? = null) : ModelAbstractItem<Address, AddressItem.ViewHolder>(model) {

    override var identifier = if(model.addressId.isNullOrBlank()) -1 else model.addressId.to64BitHash()

    override val layoutRes: Int
        get() = R.layout.item_address_v2

    override val type: Int
        get() = R.id.item_address

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        holder.cardViewContent.isEnabled = false
        holder.tvAddressName.text = if(model.addressLabel.isNullOrBlank()) "-" else model.addressLabel
        holder.tvReceiverName.text = if(model.receiverName.isNullOrBlank()) "-" else model.receiverName
        holder.tvReceiverPhoneNumber.text = if(model.receiverPhoneNumber.isNullOrBlank()) "-" else model.receiverPhoneNumber
        holder.tvAddressDetail.text = if(model.addressDetail.isNullOrBlank()) "-" else model.addressDetail

        holder.btnChooseAddress.visibility = if(selectedAddressId != model.addressId) View.VISIBLE else View.GONE
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.tvAddressName.text = null
        holder.tvReceiverName.text = null
        holder.tvReceiverPhoneNumber.text = null
        holder.tvAddressDetail.text = null

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var cardViewContent: MaterialCardView = view.findViewById(R.id.cv_content)
        var tvAddressName: TextView = view.findViewById(R.id.tv_address_name)
        var tvReceiverName: TextView = view.findViewById(R.id.tv_receiver_name)
        var tvReceiverPhoneNumber: TextView = view.findViewById(R.id.tv_receiver_phone_number)
        var tvAddressDetail: TextView = view.findViewById(R.id.tv_address_detail)
        var btnChooseAddress: Button = view.findViewById(R.id.btn_choose_address)
        var btnEditAddress: ImageButton = view.findViewById(R.id.btn_edit_address)
        var btnDeleteAddress: ImageButton = view.findViewById(R.id.btn_delete_address)

    }

}
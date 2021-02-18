package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class ChangeBankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var imBank: ImageView
    var tvAccountNumber: TextView
    var tvAccountName: TextView
    var btnChooseAddress: Button
    var editAddress: ImageButton
    var deleteAddress: ImageButton
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_choose_address -> Toast.makeText(v.context, "Choose", Toast.LENGTH_SHORT)
                .show()
            R.id.im_edit_address -> Toast.makeText(v.context, "Edit", Toast.LENGTH_SHORT).show()
            R.id.im_delete_address -> Toast.makeText(v.context, "Delete", Toast.LENGTH_SHORT).show()
        }
    }

    init {
        imBank = itemView.findViewById(R.id.imageView4)
        tvAccountNumber = itemView.findViewById(R.id.tv_account_number_confirm_payment)
        tvAccountName = itemView.findViewById(R.id.tv_account_name_confirm_payment)
        btnChooseAddress = itemView.findViewById(R.id.btn_choose_bank)
        editAddress = itemView.findViewById(R.id.im_edit_bank)
        deleteAddress = itemView.findViewById(R.id.im_delete_bank)
        btnChooseAddress.setOnClickListener(this)
        editAddress.setOnClickListener(this)
        deleteAddress.setOnClickListener(this)
    }
}
package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class ChangeAddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var tvLabelAddress: TextView
    var tvName: TextView
    var tvNoHp: TextView
    var tvDetailAddress: TextView
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
        tvLabelAddress = itemView.findViewById(R.id.tv_label_address)
        tvName = itemView.findViewById(R.id.tv_name_address)
        tvNoHp = itemView.findViewById(R.id.tv_nohp_address)
        tvDetailAddress = itemView.findViewById(R.id.tv_detail_address)
        btnChooseAddress = itemView.findViewById(R.id.btn_choose_address)
        editAddress = itemView.findViewById(R.id.im_edit_address)
        deleteAddress = itemView.findViewById(R.id.im_delete_address)
        btnChooseAddress.setOnClickListener(this)
        editAddress.setOnClickListener(this)
        deleteAddress.setOnClickListener(this)
    }
}
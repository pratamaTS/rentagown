package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.ChangeAddressViewHolder
import com.example.rentagown.Adapter.ViewHolder.ChangeBankViewHolder
import com.example.rentagown.Model.ChangeAddress
import com.example.rentagown.R
import com.example.rentagown.Response.GetAddress.DataAddress
import com.example.rentagown.Response.GetBank.DataBank
import com.squareup.picasso.Picasso

class ChangeBankAdapter(
    private val changeAddressList: ArrayList<DataBank>,
    var context: Context
) :
    RecyclerView.Adapter<ChangeBankViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeBankViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_bank, parent, false)
        return ChangeBankViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChangeBankViewHolder, position: Int) {
        if(changeAddressList[position].pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = "http://absdigital.id:5000" + changeAddressList[position].pathPhoto
            Picasso.get().load(imgURL).into(holder.imBank)
        }
        holder.tvAccountNumber.setText(changeAddressList[position].accountNumber)
        holder.tvAccountName.setText("a.n " + changeAddressList[position].accountName)
    }

    override fun getItemCount(): Int {
        return changeAddressList.size
    }
}
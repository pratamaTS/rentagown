package com.example.rentagown.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ViewHolder.CategoryMenuViewHolder
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.R
import com.example.rentagown.Response.ProductCategory.DataProductCategory

class CategoryMenuAdapter(internal val reloadItemInterface: ReloadItemInterface, categoryMenuList: ArrayList<DataProductCategory>, listener: ItemClickListener?) : RecyclerView.Adapter<CategoryMenuViewHolder>() {
    private val categoryMenuList: ArrayList<DataProductCategory>
    private var listener: ItemClickListener? = null
    var selectedCategory: DataProductCategory? = null

    interface ReloadItemInterface {
        fun passReloadItem(namaCategory: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMenuViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_title_menu, parent, false)
        return CategoryMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryMenuViewHolder, position: Int) {
        holder.tvTitle.setText(categoryMenuList[position].nameProductCategory?.capitalize()?.trimEnd())
        if (selectedCategory != null) {
            if (selectedCategory!!.nameProductCategory == categoryMenuList[position].nameProductCategory) {
                holder.tvTitle.setTextColor(Color.parseColor("#E6B31E")) //warna kuning
                reloadItemInterface.passReloadItem(selectedCategory!!.nameProductCategory.toString())
            } else {
                holder.tvTitle.setTextColor(Color.parseColor("#747474")) //warna hitam
            }
        }
        holder.container.setOnClickListener(View.OnClickListener { v ->
            if (listener != null) listener!!.onClick(v, position, false)
            selectCategory(categoryMenuList[position])
        })
    }

    override fun getItemCount(): Int {
        return categoryMenuList.size
    }

    fun getItem(position: Int): DataProductCategory {
        return categoryMenuList[position]
    }

    fun selectCategory(selected: DataProductCategory?) {
        selectedCategory = selected
        notifyDataSetChanged()
    }

    fun selectCategory(position: Int) {
        selectCategory(getItem(position))
    }

    init {
        this.categoryMenuList = categoryMenuList
    }
}
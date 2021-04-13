package com.example.rentagown.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.ProductGownActivity
import com.example.rentagown.Adapter.ViewHolder.CategoryMenuViewHolder
import com.example.rentagown.Interface.ItemClickListener
import com.example.rentagown.Model.CategoryMenu
import com.example.rentagown.R

class CategoryProductAdapter(internal val reloadItemInterface: ProductGownActivity,
                             private val categoryMenuList: ArrayList<CategoryMenu>,
                             private val listener: ItemClickListener?
) :
    RecyclerView.Adapter<CategoryMenuViewHolder>() {
    var selectedCategory: CategoryMenu? = null

    interface ReloadItemInterface {
        fun passReloadItem(namaCategory: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMenuViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chips_category, parent, false)
        return CategoryMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryMenuViewHolder, position: Int) {
        holder.tvTitle.setText(categoryMenuList[position].titleCategory)
        if (selectedCategory != null) {
            if (selectedCategory!!.titleCategory == categoryMenuList[position].titleCategory) {
                holder.tvTitle.setTextColor(Color.parseColor("#FFFFFF")) //warna putih
                holder.container.setBackgroundResource(R.drawable.bg_button_rounded_gold)
                reloadItemInterface.passReloadItem(selectedCategory!!.titleCategory.decapitalize().trim())
            } else {
                holder.tvTitle.setTextColor(Color.parseColor("#000000")) //warna hitam
                holder.container.setBackgroundResource(R.drawable.bg_button_rounded_black_outlined)
            }
        }else{
            if (position == 0) {
                holder.tvTitle.setTextColor(Color.parseColor("#FFFFFF")) //warna putih
                holder.container.setBackgroundResource(R.drawable.bg_button_rounded_gold)
                reloadItemInterface.passReloadItem("all")
            } else {
                holder.tvTitle.setTextColor(Color.parseColor("#000000")) //warna hitam
                holder.container.setBackgroundResource(R.drawable.bg_button_rounded_black_outlined)
            }
        }
        holder.container.setOnClickListener(View.OnClickListener { v ->
            listener?.onClick(v, position, false)
            selectCategory(categoryMenuList[position])
        })
    }

    override fun getItemCount(): Int {
        return categoryMenuList.size
    }

    fun getItem(position: Int): CategoryMenu {
        return categoryMenuList[position]
    }

    fun selectCategory(selected: CategoryMenu?) {
        selectedCategory = selected
        notifyDataSetChanged()
    }

    fun selectCategory(position: Int) {
        selectCategory(getItem(position))
    }

    fun addItem(dataObj: CategoryMenu, index: Int) {
        categoryMenuList.add(index, dataObj)
        notifyItemInserted(index)
    }
}
package com.example.rentagown.Adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R

class CategoryMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitle: TextView
    var container: View

    init {
        container = itemView.findViewById(R.id.category_menu_item_container)
        tvTitle = itemView.findViewById(R.id.tv_title_menu)
    }
}
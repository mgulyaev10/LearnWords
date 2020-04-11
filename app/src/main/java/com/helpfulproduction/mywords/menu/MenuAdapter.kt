package com.helpfulproduction.mywords.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.R

class MenuAdapter(
    private val items: List<MenuItem>
): RecyclerView.Adapter<BaseMenuViewHolder<MenuItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMenuViewHolder<MenuItem> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val viewHolder = when (viewType) {
            R.layout.menu_list_item -> MenuListViewHolder(
                view
            )
            else -> throw IllegalStateException("Unsupported type")
        }
        return viewHolder as BaseMenuViewHolder<MenuItem>
    }

    override fun onBindViewHolder(holder: BaseMenuViewHolder<MenuItem>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }
}
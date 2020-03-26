package com.example.clickingbad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.clickingbad.R
import com.example.clickingbad.business_logic.models.LaunderingItem
import com.example.clickingbad.utils.formatCost
import com.example.clickingbad.utils.launderingValuesHtml
import kotlinx.android.synthetic.main.rv_item_default.view.*

class LaunderingListAdapter() :
    RecyclerView.Adapter<LaunderingListAdapter.ViewHolder>() {

    private var list: List<LaunderingItem> = emptyList()
    private val expanded = HashSet<Int>()

    fun submitList(data: List<LaunderingItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_default, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upgrade: LaunderingItem = list[position]
        holder.bind(upgrade, expanded.contains(position))

        holder.itemView.setOnClickListener {
            if (expanded.contains(position)) expanded.remove(position)
            else expanded.add(position)

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(upgrade: LaunderingItem, expanded: Boolean) {
            itemView.resource_name.text = upgrade.label
            itemView.resource_cost.text = formatCost(upgrade.cost!!)

            itemView.item_info.isVisible = expanded
            itemView.item_info.text = upgrade.description

            itemView.resource_quantity.text = upgrade.amount.toString()
            itemView.resource_values.text = launderingValuesHtml(upgrade.rps!!)
        }
    }

}

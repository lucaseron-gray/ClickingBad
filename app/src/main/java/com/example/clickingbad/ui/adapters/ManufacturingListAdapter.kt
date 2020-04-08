package com.example.clickingbad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.clickingbad.R
import com.example.clickingbad.business_logic.models.ManufacturingItem
import com.example.clickingbad.utils.formatCost
import com.example.clickingbad.utils.manufacturingValuesHtml
import kotlinx.android.synthetic.main.rv_item_default.view.*

class ManufacturingListAdapter() :
    RecyclerView.Adapter<ManufacturingListAdapter.ViewHolder>() {

    private var list: List<ManufacturingItem> = emptyList()
    private val expanded = HashSet<Int>()

    fun submitList(data: List<ManufacturingItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_default, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upgrade: ManufacturingItem = list[position]
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

        fun bind(upgrade: ManufacturingItem, expanded: Boolean) {
            itemView.resource_name.text = upgrade.label
            itemView.resource_cost.text = formatCost(upgrade.cost)

            itemView.item_info.isVisible = expanded
            itemView.item_info.text = upgrade.description

            itemView.resource_quantity.text = upgrade.amount.toString()
            itemView.resource_values.text = manufacturingValuesHtml(upgrade.rps, upgrade.risk)

            when (upgrade.amount) {
                0 -> itemView.button_sell.isVisible = false
                else -> itemView.button_sell.isVisible = true
            }
        }
    }

}

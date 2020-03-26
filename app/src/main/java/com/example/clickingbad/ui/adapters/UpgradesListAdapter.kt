package com.example.clickingbad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.clickingbad.R
import com.example.clickingbad.business_logic.models.UpgradesItem
import com.example.clickingbad.utils.formatCost
import kotlinx.android.synthetic.main.rv_item_default.view.item_info
import kotlinx.android.synthetic.main.rv_item_default.view.resource_cost
import kotlinx.android.synthetic.main.rv_item_default.view.resource_name
import kotlinx.android.synthetic.main.rv_item_upgrades.view.*

class UpgradesListAdapter() :
    RecyclerView.Adapter<UpgradesListAdapter.ViewHolder>() {

    private var list: List<UpgradesItem> = emptyList()
    private val expanded = HashSet<Int>()

    fun submitList(data: List<UpgradesItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_upgrades, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upgrade: UpgradesItem = list[position]
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

        fun bind(upgrade: UpgradesItem, expanded: Boolean) {
            itemView.resource_name.text = upgrade.label
            itemView.resource_cost.text = formatCost(upgrade.cost!!)

            when (upgrade.purchased) {
                true -> {
                    itemView.resource_purchased.setImageResource(R.drawable.checkmark_green)
                    itemView.button_upgrades_purchase.isVisible = false
                }
                else -> {
                    itemView.resource_purchased.setImageResource(R.drawable.checkmark_gray)
                    itemView.button_upgrades_purchase.isVisible = true
                }
            }

            itemView.item_info.isVisible = expanded
            itemView.item_info.text = upgrade.description
        }
    }

}

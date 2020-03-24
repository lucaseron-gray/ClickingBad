package com.example.clickingbad.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.clickingbad.R
import com.example.clickingbad.models.Manufacturing
import com.example.clickingbad.utils.*
import kotlinx.android.synthetic.main.rv_item_default.view.*

class ManufacturingViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.rv_item_default, parent, false)) {

    fun bind(upgrade: Manufacturing, expanded: Boolean) {
        itemView.resource_name.text = upgrade.label
        itemView.resource_cost.text = formatCost(upgrade.cost)

        itemView.item_info.isVisible = expanded
        itemView.item_info.text = upgrade.description

        itemView.resource_quantity.text = upgrade.amount.toString()
        itemView.resource_values.text = formatToHtml(upgrade.rps, upgrade.risk)
    }
}

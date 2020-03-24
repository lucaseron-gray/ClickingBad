package com.example.clickingbad.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clickingbad.models.Manufacturing

class ManufacturingListAdapter(private val list: List<Manufacturing>) : RecyclerView.Adapter<ManufacturingViewHolder>() {

    val expanded = HashSet<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManufacturingViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ManufacturingViewHolder, position: Int) {
        val upgrade: Manufacturing = list[position]
        holder.bind(upgrade, expanded.contains(position))

        holder.itemView.setOnClickListener{
            if (expanded.contains(position)) expanded.remove(position)
            else expanded.add(position)

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = list.size
}

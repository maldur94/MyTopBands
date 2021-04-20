package com.pbartkowiak.mytopbands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbartkowiak.mytopbands.core.ItemCallback
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.databinding.ItemBandBinding
import com.pbartkowiak.mytopbands.util.BaseListAdapter

class BandListAdapter : BaseListAdapter<Band, BandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemBandBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).run { BandViewHolder(this) }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        holder.bind(items[position], callback)
    }
}

class BandViewHolder(private val binding: ItemBandBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(operation: Band, callback: ItemCallback<Band>) {
        BandItemViewModel(callback).run {
            binding.viewModel = this
            this.bind(operation)
        }
    }
}

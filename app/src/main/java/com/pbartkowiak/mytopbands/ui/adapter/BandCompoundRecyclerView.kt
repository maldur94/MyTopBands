package com.pbartkowiak.mytopbands.ui.adapter

import android.content.Context
import android.util.AttributeSet
import com.pbartkowiak.mytopbands.core.ItemCallback
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.util.CompoundRecyclerView

class BandCompoundRecyclerView(context: Context, attrs: AttributeSet) :
    CompoundRecyclerView<Band>(context, attrs) {

    private val adapter = BandListAdapter()

    init {
        init(adapter)
    }

    override fun updateResults(list: List<Band>) {
        adapter.updateItems(list)
    }

    override fun setRecyclerViewCallback(callback: ItemCallback<Band>) {
        adapter.callback = callback
    }
}

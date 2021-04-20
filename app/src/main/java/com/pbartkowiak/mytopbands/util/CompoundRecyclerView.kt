package com.pbartkowiak.mytopbands.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.ItemCallback
import kotlinx.android.synthetic.main.view_list.view.*

abstract class CompoundRecyclerView<T>(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {

    private lateinit var recyclerView: RecyclerView

    abstract fun updateResults(list: List<T>)
    abstract fun setRecyclerViewCallback(callback: ItemCallback<T>)

    protected fun init(adapter: BaseListAdapter<*, *>) {
        recyclerView = View.inflate(context, R.layout.view_list, this).recyclerView
            .apply {
                this@apply.adapter = adapter
                val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(context, R.drawable.divider)?.run {
                    divider.setDrawable(this)
                }
                addItemDecoration(divider)
            }
    }
}

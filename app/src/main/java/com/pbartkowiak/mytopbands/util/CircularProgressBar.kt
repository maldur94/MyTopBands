package com.pbartkowiak.mytopbands.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.pbartkowiak.mytopbands.R

private const val PROGRESS_BAR_STROKE_WIDTH = 5f
private const val PROGRESS_BAR_CENTER_RADIUS = 30f

class CircularProgressBar(private val context: Context) {

    fun init(): CircularProgressDrawable {
        val progressBar = CircularProgressDrawable(context)
        progressBar.strokeWidth = PROGRESS_BAR_STROKE_WIDTH
        progressBar.centerRadius = PROGRESS_BAR_CENTER_RADIUS
        progressBar.setColorSchemeColors(context.getColor(R.color.secondary))
        progressBar.start()
        return progressBar
    }
}

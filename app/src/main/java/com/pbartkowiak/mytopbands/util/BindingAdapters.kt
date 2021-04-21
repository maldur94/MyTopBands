package com.pbartkowiak.mytopbands.util

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.ItemCallback

@BindingAdapter("modelList")
fun <I> CompoundRecyclerView<I>.updateResults(list: ArrayList<I>?) {
    list?.run { updateResults(this) }
}

@BindingAdapter("callback")
fun <I> CompoundRecyclerView<I>.setRecyclerViewCallback(callback: ItemCallback<I>?) {
    callback?.run { setRecyclerViewCallback(this) }
}

@BindingAdapter("imageSrc")
fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url).centerCrop()
        .placeholder(CircularProgressBar(context).init())
        .error(R.mipmap.ic_no_image_foreground)
        .into(this)
}

@BindingAdapter("webUrl")
fun WebView.loadWeb(url: String) {
    loadUrl(url)
    webViewClient = WebViewClient()
}

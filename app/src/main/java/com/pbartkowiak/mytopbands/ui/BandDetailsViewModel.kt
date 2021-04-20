package com.pbartkowiak.mytopbands.ui

import androidx.lifecycle.ViewModel
import com.pbartkowiak.mytopbands.util.ObservableString

class BandDetailsViewModel : ViewModel() {

    val websiteUrl = ObservableString()

    fun setupDetailView(websiteUrl: String?) {
        this.websiteUrl.set(websiteUrl)
    }
}

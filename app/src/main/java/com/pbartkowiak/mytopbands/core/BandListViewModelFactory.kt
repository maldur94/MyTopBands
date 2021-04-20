package com.pbartkowiak.mytopbands.core

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pbartkowiak.mytopbands.ui.BandListViewModel
import com.pbartkowiak.mytopbands.ui.BandMicroService

@Suppress("UNCHECKED_CAST")
class BandListViewModelFactory(
    private val resources: Resources,
    private val microService: BandMicroService
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(BandListViewModel::class.java)) {
            BandListViewModel(resources, microService) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}

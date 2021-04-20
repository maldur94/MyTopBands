package com.pbartkowiak.mytopbands.core.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pbartkowiak.mytopbands.core.BandListViewModelFactory
import com.pbartkowiak.mytopbands.ui.BandMicroService

abstract class BaseActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun <T : ViewModel> getViewModel(
        viewModelClass: Class<T>,
        resources: Resources,
        microService: BandMicroService
    ) = ViewModelProvider(this, BandListViewModelFactory(resources, microService))
        .get(viewModelClass)

    protected fun <T : ViewModel> getViewModel(
        viewModelClass: Class<T>
    ) = ViewModelProvider(this).get(viewModelClass)
}

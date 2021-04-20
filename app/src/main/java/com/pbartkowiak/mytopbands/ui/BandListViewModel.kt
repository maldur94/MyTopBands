package com.pbartkowiak.mytopbands.ui

import android.content.res.Resources
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.ItemCallback
import com.pbartkowiak.mytopbands.core.data.Resource
import com.pbartkowiak.mytopbands.core.data.ResourceStatus
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.util.HyperlinkFinder
import com.pbartkowiak.mytopbands.util.event.EmptyEvent
import com.pbartkowiak.mytopbands.util.event.Event
import com.pbartkowiak.mytopbands.util.event.Event2
import timber.log.Timber

class BandListViewModel(private val resources: Resources, val microService: BandMicroService) :
    ViewModel(), ItemCallback<Band> {

    val bandList = ObservableArrayList<Band>()

    val loadingStatus = ResourceStatus.zip(microService.bands)

    val refreshBands: LiveData<EmptyEvent>
        get() = mutableRefreshBands
    val proceedBandChosen: LiveData<Event<String>>
        get() = mutableProceedBandChosen
    val showInternetConnectionErrorDialog: MutableLiveData<Event2<Int, String>>
        get() = mutableShowInternetConnectionErrorDialog

    private val mutableRefreshBands = MutableLiveData<EmptyEvent>()
    private val mutableProceedBandChosen = MutableLiveData<Event<String>>()
    private val mutableShowInternetConnectionErrorDialog = MutableLiveData<Event2<Int, String>>()

    init {
        callForBands()
    }

    override fun onItemClick(item: Band) {
        mutableProceedBandChosen.value = Event(HyperlinkFinder.getUrl(item.description))
    }

    fun refreshBands() {
        microService.callBands()
    }

    fun callForBands() {
        microService.callBands()
    }

    fun onBandsDownloaded(bands: Resource<List<Band>>?) {
        val data = bands?.data
        val message = bands?.message ?: ""
        if (isStatusError(bands?.status)) {
            Timber.e(resources.getString(R.string.connection_error_no_internet))
            showInternetConnectionErrorDialog(message)
        } else if (!data.isNullOrEmpty()) {
            bandList.clear()
            this.bandList.addAll(data.sortedBy { it.orderId })
        }
    }

    private fun isStatusError(status: ResourceStatus?) = status == ResourceStatus.ERROR

    private fun showInternetConnectionErrorDialog(message: String) {
        mutableShowInternetConnectionErrorDialog.value = Event2(
            R.string.connection_error_dialog_title,
            message
        )
    }
}

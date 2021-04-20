package com.pbartkowiak.mytopbands.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.pbartkowiak.mytopbands.data.repository.BandRepository
import com.pbartkowiak.mytopbands.util.event.EmptyEvent

class BandMicroService(private val bandRepository: BandRepository) {

    private val mutableBands = MutableLiveData<EmptyEvent>()

    val bands = mutableBands.switchMap {
        bandRepository.loadAllBands()
    }

    internal fun callBands() {
        mutableBands.value = EmptyEvent()
    }
}

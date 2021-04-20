package com.pbartkowiak.mytopbands.data.repository

import com.pbartkowiak.mytopbands.data.BandDao
import com.pbartkowiak.mytopbands.core.AppExecutors
import com.pbartkowiak.mytopbands.core.data.BoundResource
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.data.source.BandService

class BandRepository(
    private val appExecutors: AppExecutors,
    private val bandService: BandService,
    private val bandDao: BandDao
) {

    fun loadAllBands() = object : BoundResource<List<Band>, List<Band>>(appExecutors) {
        override fun saveCallResult(items: List<Band>) {
            bandDao.insertBands(items)
        }

        override fun shouldFetch(data: List<Band>?) = data == null || data.isNullOrEmpty()

        override fun loadFromDb() = bandDao.getAllBands()

        override fun createCall() = bandService.getAllBands()
    }.asLiveData()
}

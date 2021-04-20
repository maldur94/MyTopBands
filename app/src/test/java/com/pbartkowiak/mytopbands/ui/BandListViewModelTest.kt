package com.pbartkowiak.mytopbands.ui

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.BaseViewModelTest
import com.pbartkowiak.mytopbands.core.data.Resource
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.data.model.Origin
import com.pbartkowiak.mytopbands.data.repository.BandRepository
import com.pbartkowiak.mytopbands.util.event.Event
import com.pbartkowiak.mytopbands.util.event.Event2
import com.pbartkowiak.mytopbands.utils.VerifyUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class BandListViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: BandListViewModel
    private lateinit var resources: Resources
    private lateinit var microService: BandMicroService
    private lateinit var bandRepository: BandRepository

    @Before
    override fun setup() {
        super.setup()
        resources = mock()
        bandRepository = mock()
        microService = BandMicroService(bandRepository)
        viewModel = BandListViewModel(resources, microService)
    }

    @Test
    fun `When onItemClick is called proceedBandChosen is proceeded with extracted hyperlink from band description`() {
        // given
        val item = prepareItem()
        val linkExpected = "http://testurl.com"

        // when
        viewModel.onItemClick(item)

        // then
        val captor = argumentCaptor<Event<String>>()
        VerifyUtils.verify(viewModel.proceedBandChosen, captor)
        assertEquals(linkExpected, viewModel.proceedBandChosen.value!!.peek())
    }

    @Test
    fun `When refreshBands is called bands is filled up with proper band data`() {
        // given
        val item = prepareItem()
        val bandsExpected = MutableLiveData(Resource.success(listOf(item)))
        Mockito.`when`(bandRepository.loadAllBands()).thenReturn(bandsExpected)

        // when
        viewModel.refreshBands()

        // then
        VerifyUtils.verify(viewModel.microService.bands)
        assertEquals(bandsExpected.value, viewModel.microService.bands.value)
    }

    @Test
    fun `When callForBands is called bands is filled up with proper band data`() {
        // given
        val item = prepareItem()
        val bandsExpected = MutableLiveData(Resource.success(listOf(item)))
        Mockito.`when`(bandRepository.loadAllBands()).thenReturn(bandsExpected)

        // when
        viewModel.callForBands()

        // then
        VerifyUtils.verify(viewModel.microService.bands)
        assertEquals(bandsExpected.value, viewModel.microService.bands.value)
    }

    @Test
    fun `When onBandsDownloaded is called with non empty success resource status, bandList is updated`() {
        // given
        val item1 = prepareItem(orderId = 2)
        val item2 = prepareItem(orderId = 4)
        val item3 = prepareItem(orderId = 3)
        val bandsExpected = listOf(item1, item3, item2)
        val bandResource = Resource.success(listOf(item1, item2, item3))

        // when
        viewModel.onBandsDownloaded(bandResource)

        // then
        assertEquals(bandsExpected, viewModel.bandList)
    }

    @Test
    fun `When onBandsDownloaded is called with error resource status, proper error message is shown`() {
        // given
        val messageExpected = "Error message"
        val titleExpected = R.string.connection_error_dialog_title
        val bandResource = Resource.error(messageExpected, emptyList<Band>())

        // when
        viewModel.onBandsDownloaded(bandResource)

        // then
        val captor = argumentCaptor<Event2<Int, String>>()
        VerifyUtils.verify(viewModel.showInternetConnectionErrorDialog, captor)
        assertEquals(titleExpected, captor.firstValue.peek1())
        assertEquals(messageExpected, captor.firstValue.peek2())
    }

    private fun prepareItem(orderId: Int = 0) = Band(
        orderId = orderId,
        name = "Band Title",
        genres = "Test genres",
        yearsActive = "2000 - 2001",
        origin = Origin("Poland", "Poznan"),
        description = "Band description test",
        imageUrl = "http://imageurl.com"
    )
}

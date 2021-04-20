package com.pbartkowiak.mytopbands.ui

import com.pbartkowiak.mytopbands.core.BaseViewModelTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BandDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: BandDetailsViewModel

    @Before
    override fun setup() {
        super.setup()
        viewModel = BandDetailsViewModel()
    }

    @Test
    fun `When setupDetailView is called urlAddress is filled up with proper url`() {
        // given
        val urlExpected = "http://testurl.com"

        // when
        viewModel.setupDetailView(urlExpected)

        // then
        assertEquals(urlExpected, viewModel.websiteUrl.get())
    }
}

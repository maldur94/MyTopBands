package com.pbartkowiak.mytopbands.data.source

import androidx.lifecycle.LiveData
import com.pbartkowiak.mytopbands.core.API_DATA_SIZE
import com.pbartkowiak.mytopbands.core.API_PAGE
import com.pbartkowiak.mytopbands.core.data.remote.ApiResponse
import com.pbartkowiak.mytopbands.data.model.Band
import retrofit2.http.GET
import retrofit2.http.Query

interface BandService {

    @GET("/data/bands.json")
    fun getAllBands(
        @Query("page") page: Long = API_PAGE,
        @Query("size") size: Long = API_DATA_SIZE
    ): LiveData<ApiResponse<List<Band>>>
}

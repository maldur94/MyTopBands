package com.pbartkowiak.mytopbands.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbartkowiak.mytopbands.core.data.local.BaseDao
import com.pbartkowiak.mytopbands.data.model.Band

@Dao
abstract class BandDao : BaseDao<Band> {

    @Query("SELECT * FROM bands")
    abstract fun getAllBands(): LiveData<List<Band>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBands(bands: List<Band>)
}

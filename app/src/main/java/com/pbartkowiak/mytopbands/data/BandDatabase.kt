package com.pbartkowiak.mytopbands.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pbartkowiak.mytopbands.core.data.local.DatabaseTypeConverters
import com.pbartkowiak.mytopbands.data.model.Band

@Database(entities = [Band::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverters::class)
abstract class BandDatabase : RoomDatabase() {

    abstract fun bandsDao(): BandDao
}

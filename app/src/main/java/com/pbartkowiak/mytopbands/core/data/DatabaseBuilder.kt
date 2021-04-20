package com.pbartkowiak.mytopbands.core.data

import android.content.Context
import androidx.room.Room
import com.pbartkowiak.mytopbands.data.BandDatabase
import com.pbartkowiak.mytopbands.R

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: BandDatabase? = null

    fun build(context: Context): BandDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BandDatabase::class.java,
                    context.getString(R.string.config_database_name)
                )
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}

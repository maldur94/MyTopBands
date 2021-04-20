package com.pbartkowiak.mytopbands.core.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pbartkowiak.mytopbands.data.model.Origin

class DatabaseTypeConverters {

    @TypeConverter
    fun toOriginType(json: String?) = Gson().fromJson(json, Origin::class.java)

    @TypeConverter
    fun fromOriginType(value: Origin?) = Gson().toJson(value)
}
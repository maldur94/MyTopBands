package com.pbartkowiak.mytopbands.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbartkowiak.mytopbands.core.HasItemId
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Suppress("ConstructorParameterNaming")
@Entity(tableName = "bands")
@Parcelize
data class Band(
    @PrimaryKey @ColumnInfo(name = "orderId") val orderId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "yearsActive") val yearsActive: String,
    @ColumnInfo(name = "origin") val origin: @RawValue Origin,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "description") val description: String,
) : HasItemId<Int>, Parcelable {
    override fun getItemId() = orderId
}

data class Origin(val country: String, val city: String)

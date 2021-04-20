package com.pbartkowiak.mytopbands.ui.adapter

import com.pbartkowiak.mytopbands.core.ItemCallback
import com.pbartkowiak.mytopbands.data.model.Band
import com.pbartkowiak.mytopbands.util.ObservableString

class BandItemViewModel(private var callback: ItemCallback<Band>) {

    val name = ObservableString()
    val genres = ObservableString()
    val yearsActive = ObservableString()
    val origin = ObservableString()
    val imageUrl = ObservableString()
    val description = ObservableString()

    var band: Band? = null

    fun onItemClick() {
        band?.run { callback.onItemClick(this) }
    }

    fun bind(band: Band) {
        this.band = band
        name.set(band.name)
        genres.set(band.genres)
        yearsActive.set(band.yearsActive)
        origin.set("${band.origin.country}, ${band.origin.city}")
        imageUrl.set(band.imageUrl)
        description.set(band.description)
    }
}

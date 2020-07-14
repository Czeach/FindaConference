package com.example.findaconference.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConferenceItem(
    val conference_code: String,
    val date: String,
    val description: String,
    val image: String,
    val isFavourite: Boolean,
    val name: String,
    val registration: String,
    val venue: String
): Parcelable
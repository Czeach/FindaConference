package com.example.findaconference.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LitigationItem(
    val conference_code: String,
    val date: String,
    val description: String,
    val image: String,
    val name: String,
    val registration: String,
    val venue: String
): Parcelable
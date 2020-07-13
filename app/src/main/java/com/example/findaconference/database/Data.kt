package com.example.findaconference.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouritesList")
data class Favourites(

    @PrimaryKey(autoGenerate = true)
    private var id: Int,

    @ColumnInfo(name = "conf_image")
    val image: String,

    @ColumnInfo(name = "conf_name")
    val name: String,

    @ColumnInfo(name = "conf_venue")
    val venue: String
)
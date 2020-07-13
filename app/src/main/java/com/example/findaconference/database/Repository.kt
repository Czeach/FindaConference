package com.example.findaconference.database

import androidx.lifecycle.LiveData

class FavouritesRepository(private val favouritesDao: FavouritesDao) {

    val favourites: LiveData<List<Favourites>> = favouritesDao.getFavoriteData()

    suspend fun insert(favourites: Favourites) {
        favouritesDao.insert(favourites)
    }
}
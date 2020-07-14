package com.example.findaconference

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.findaconference.database.Favourites
import com.example.findaconference.database.FavouritesDao
import com.example.findaconference.database.FavouritesDatabase
import com.example.findaconference.database.FavouritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(application: Application): AndroidViewModel(application) {

    private var repository: FavouritesRepository? = null

    var favourites: LiveData<List<Favourites>>? = null

    init {
        val favouritesDao =
            FavouritesDatabase.getDatabase(application, viewModelScope).favouriteDao()
        repository = FavouritesRepository(favouritesDao)
        favourites = repository!!.favourites
    }

    fun insert(favourites: Favourites) = viewModelScope.launch(Dispatchers.IO) {
        repository?.insert(favourites)
    }
}
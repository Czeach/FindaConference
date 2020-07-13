package com.example.findaconference

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.findaconference.database.Favourites
import com.example.findaconference.database.FavouritesDao
import com.example.findaconference.database.FavouritesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var  repository: FavouritesRepository

    lateinit var allFavourites: LiveData<List<Favourites>>

    fun insert(favourites: Favourites) = viewModelScope.launch {
        repository.insert(favourites)
    }

}
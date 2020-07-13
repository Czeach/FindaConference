package com.example.findaconference.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavouritesDao {

    @Insert
    fun insert(favoriteList: Favourites)

    @Query("SELECT * from favouritesList")
    fun getFavoriteData(): LiveData<List<Favourites>>

    @Query("SELECT EXISTS (SELECT 1 FROM favouritesList WHERE id = :id)")
    fun isFavorite(id: Int): Int

    @Query("DELETE FROM favouritesList")
    fun delete()
}
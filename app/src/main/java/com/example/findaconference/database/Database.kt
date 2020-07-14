package com.example.findaconference.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Favourites::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase: RoomDatabase() {

    abstract fun favouriteDao(): FavouritesDao

    companion object {
        @Volatile
        private var INSTANCE: FavouritesDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FavouritesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouritesDatabase::class.java,
                    "word_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.favouriteDao())
                    }
                }
            }
        }

        fun populateDatabase(favouritesDao: FavouritesDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            favouritesDao.delete()
        }
    }

}
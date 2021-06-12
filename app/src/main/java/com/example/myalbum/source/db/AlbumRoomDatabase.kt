package com.example.myalbum.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwordsample.AlbumDao

/*
* Database class which provide room database.
* */
@Database(entities = arrayOf(Album::class), exportSchema = false, version = 1)
abstract class AlbumRoomDatabase: RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        private var INSTANCE: AlbumRoomDatabase? = null

        fun getInstance(
            context: Context
        ): AlbumRoomDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumRoomDatabase::class.java,
                    "albumDB"
                ).allowMainThreadQueries().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
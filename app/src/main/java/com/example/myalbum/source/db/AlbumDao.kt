package com.example.roomwordsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myalbum.source.db.Album

/*
* DB Dao class which abstracts the DB operations.
* */
@Dao
interface AlbumDao {

    @Query("SELECT * FROM album_table ORDER BY title ASC")
    fun getAllAlbums(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(album: List<Album>)

    @Query("DELETE from album_table")
    fun deleteAll()
}
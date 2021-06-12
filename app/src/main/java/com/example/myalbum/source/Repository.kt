package com.example.myalbum.source

import androidx.lifecycle.LiveData
import com.example.myalbum.model.response.Album
import com.example.roomwordsample.AlbumDao
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope

/*
* Repository interface which provides a level of abstraction for the view model.
* */
interface Repository {
    fun getAlbumsFromApi(scope: CoroutineScope): Single<List<Album>?>
    fun fetchAlbumsFromDb(): LiveData<List<com.example.myalbum.source.db.Album>>
    suspend fun insertAlbum(album: com.example.myalbum.source.db.Album)
    suspend fun insertAlbums(albums: List<com.example.myalbum.source.db.Album>)
    suspend fun deleteAll()
}
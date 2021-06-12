package com.example.myalbum.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myalbum.app.App
import com.example.myalbum.model.response.Album
import com.example.myalbum.source.db.AlbumRoomDatabase
import com.example.myalbum.source.network.Api
import com.example.roomwordsample.AlbumDao
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* Implementation of the Repository interface which provides a level of abstraction for the view model.
* */
class RepositoryImpl @Inject constructor(val api: Api): Repository {
    private var albumDao: AlbumDao = AlbumRoomDatabase.getInstance(App.appContext)?.albumDao()

    override fun getAlbumsFromApi(scope: CoroutineScope): Single<List<Album>?> {
        return api.getAlbums().map { it }
    }

    override fun fetchAlbumsFromDb(): LiveData<List<com.example.myalbum.source.db.Album>> {
        return albumDao.getAllAlbums()
    }

    override suspend fun insertAlbum(album: com.example.myalbum.source.db.Album) {
        albumDao.insertAlbum(album)
    }

    override suspend fun insertAlbums(albums: List<com.example.myalbum.source.db.Album>) {
        albumDao.insertAlbums(albums)
    }

    override suspend fun deleteAll() {
        albumDao.deleteAll()
    }
}
package com.example.myalbum.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myalbum.app.App
import com.example.myalbum.model.response.Album
import com.example.myalbum.source.Repository
import com.example.myalbum.source.db.AlbumRoomDatabase
import com.example.myalbum.utils.Utils.isOnline
import com.example.roomwordsample.AlbumDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* Home Viewmodel class in the mvvm model that supports the view.
* */
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    var albumsDBLiveData: LiveData<List<com.example.myalbum.source.db.Album>> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var albumsList: List<Album> = arrayListOf<Album>()

    fun init() {
        fetchAlbumsFromDb()
        if(isOnline(App.appContext)) {
            getAlbumsFromApi()
        }
    }

    /**
     * Fetch all the Albums list from web service.
     *
     * @param void
     * @return void
     */
    private fun getAlbumsFromApi() {
        repository.getAlbumsFromApi(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ albums ->
                if(!albums.isNullOrEmpty()) {
                    albumsList = getAlbumsSorted(albums)
                    insertAlbumsToDb(albumsList)
                }
            }, { error ->
                _errorLiveData.value = error.message
            })
            .let { compositeDisposable.add(it) }
    }

    /**
     * Fetch all the Albums list from DB.
     *
     * @param void
     * @return void
     */
    fun fetchAlbumsFromDb() {
        albumsDBLiveData = repository.fetchAlbumsFromDb()
    }

    /**
     * Insert all the Albums list into the DB.
     *
     * @param albumsList List of Album objects.
     * @return void
     */
    private fun insertAlbumsToDb(albumsList: List<Album>) {
        var dbAlbumList = albumsList?.map { album ->
            com.example.myalbum.source.db.Album(
                id = album.id?:0,
                userId = album.userId?:0,
                title = album.title?:""
            )
        }

        viewModelScope.launch {
            repository.deleteAll()
            repository.insertAlbums(dbAlbumList)
        }
    }

    /**
     * Remap all the DB Albums objects to Album pojo class for adapter.
     *
     * @param albums List of DB Album objects.
     * @return void
     */
    fun getAlbumsFromDbObj(albums: List<com.example.myalbum.source.db.Album>): List<Album> {
        return albums?.map { album ->
            Album(
                id = album.id?:0,
                userId = album.userId?:0,
                title = album.title?:""
            )
        }?:ArrayList()
    }

    /**
     * Sort the Album list alphabetically.
     *
     * @param albums List of DB Album objects.
     * @return void
     */
    fun getAlbumsSorted(albums: List<Album>): List<Album> {
        return albums.sortedBy { it.title }
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
package com.example.myalbum.home

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.myalbum.app.App
import com.example.myalbum.source.Repository
import com.example.myalbum.source.db.Album
import com.example.myalbum.ui.home.HomeViewModel
import com.example.myalbum.utils.Utils
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class HomeViewModelTest {
    private lateinit var SUT: HomeViewModel
    private var repository: Repository = mock(Repository::class.java)

    @Before
    fun setUp() {
        App.appContext = mock()
        SUT = HomeViewModel(repository)
    }

    @Test
    fun testGetAlbumsFromDbReturnsCorrectType() {
        var albumsDbObj = arrayListOf<Album>(
            Album(1, 1234, "ABC Album"),
            Album(2, 2345, "DEF Album"),
            Album(3, 4567, "GHI Album")
        )
        var albums = SUT.getAlbumsFromDbObj(albumsDbObj)
        assert((albums is List<com.example.myalbum.model.response.Album>))
    }

    @Test
    fun testGetAlbumsFromDbReturnsCorrectObjectType() {
        var albumsDbObj = arrayListOf<Album>(
            Album(1, 1234, "ABC Album"),
            Album(2, 2345, "DEF Album"),
            Album(3, 4567, "GHI Album")
        )
        var albums = SUT.getAlbumsFromDbObj(albumsDbObj)
        assert((albums?.get(0) is com.example.myalbum.model.response.Album))
        assert((albums?.get(1) is com.example.myalbum.model.response.Album))
        assert((albums?.get(2) is com.example.myalbum.model.response.Album))
    }
}
package com.example.myalbum.source.network

import com.example.myalbum.model.response.Album
import com.example.myalbum.utils.Configuration
import io.reactivex.Single
import retrofit2.http.GET

/*
* Retrofit Api interface which provides API definitions.
* */
interface Api {

    companion object {
        const val BASE_URL = Configuration.BASE_URL
    }

    @GET("albums")
    fun getAlbums(): Single<List<Album>>
}
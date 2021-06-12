package com.example.myalbum.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumsResponse(
	@SerializedName("albums")
	val albums: List<Album>? = null
) : Parcelable

@Parcelize
data class Album(
	val id: Int? = null,
	val title: String? = null,
	val userId: Int? = null
) : Parcelable

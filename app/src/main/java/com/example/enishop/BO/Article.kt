package com.example.enishop.BO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.Date

@Entity
data class Article(
    @PrimaryKey()
    var id: Long,
    @Json(name = "title")
    var name: String,
    var description: String,
    var price: Float,
    @Json(name = "image")
    var urlImage: String,
    var category: String,
    var date: Long?,

    ){





}
package com.example.enishop.BO

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Article(
    @PrimaryKey()
    var id: Long,
    var name: String,
    var description: String,
    var price: Float,
    var urlImage: String,
    var category: String,
    var date: Long?,

    ){





}
package com.example.enishop.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.enishop.BO.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article WHERE id = :id")
    fun findById(id: Long): Article?

    @Insert
     suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>

    @Delete
    fun deleteArticle(article: Article)



}
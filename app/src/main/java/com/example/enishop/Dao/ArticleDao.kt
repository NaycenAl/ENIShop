package com.example.enishop.Dao

import com.example.enishop.BO.Article

interface ArticleDao {


    fun findById(id: Long): Article?
    fun insertArticle(article: Article): Long
    fun getAllArticles(): List<Article>
}
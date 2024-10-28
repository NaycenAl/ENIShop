package com.example.enishop.repository

import com.example.enishop.BO.Article
import com.example.enishop.Dao.ArticleDao
import com.example.enishop.Dao.DaoFactory
import com.example.enishop.Dao.DaoType

class ArticleRepository {



    private val articleDao : ArticleDao = DaoFactory.createArticleDao(DaoType.MEMORY)

    fun getArticleById(id: Long): Article? {
        return articleDao.findById(id)

    }

    fun addArticle(article: Article): Long {
        return articleDao.insertArticle(article)
    }

    fun getAllArticles(): List<Article> {
        return articleDao.getAllArticles()

    }


}
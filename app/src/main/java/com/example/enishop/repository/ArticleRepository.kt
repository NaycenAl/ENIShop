package com.example.enishop.repository

import com.example.enishop.BO.Article
import com.example.enishop.Dao.ArticleDao
import com.example.enishop.Dao.DaoType
import com.example.enishop.service.ArticleService

class ArticleRepository(private val articleDaoRoomImpl: ArticleDao?,
                        private val articleDaoMemoryImpl: ArticleDao?,
                        private val articleService: ArticleService
){



    suspend fun addArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        when (type) {
            DaoType.MEMORY -> articleDaoMemoryImpl?.insertArticle(article)
            else -> articleDaoRoomImpl?.insertArticle(article)
        }
    }

    suspend fun addArticleFav(article: Article)  {
        articleDaoRoomImpl?.insertArticle(article)
    }

    suspend fun deleteArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        when (type){
            DaoType.MEMORY -> articleDaoMemoryImpl?.deleteArticle(article)
            else -> articleDaoRoomImpl?.deleteArticle(article)
        }
        articleDaoRoomImpl?.deleteArticle(article)
    }

    fun getArticleFav(id: Long) : Article?{
        return articleDaoRoomImpl?.findById(id)
    }


    suspend fun getAllArticlesFromAPI(): List<Article> {
            return articleService.getArticles()
    }

    suspend fun getArticleByIdFromAPI(id: Long): Article? {
        return articleService.getArticleById(id)
    }

    suspend fun getCategories(): List<String> {
        return articleService.getCategories()

    }

}
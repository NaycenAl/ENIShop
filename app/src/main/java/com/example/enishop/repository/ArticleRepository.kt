package com.example.enishop.repository

import com.example.enishop.BO.Article
import com.example.enishop.Dao.ArticleDao
import com.example.enishop.Dao.DaoType

class ArticleRepository(private val articleDaoRoomImpl: ArticleDao,
                        private val articleDaoMemoryImpl: ArticleDao
){



    fun getArticleById(id: Long, type: DaoType = DaoType.MEMORY): Article? {
            return when (type) {
                DaoType.MEMORY -> articleDaoMemoryImpl.findById(id)
                else -> articleDaoRoomImpl.findById(id)
            }


    }

    suspend fun addArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        when (type) {
            DaoType.MEMORY -> articleDaoMemoryImpl.insertArticle(article)
            else -> articleDaoRoomImpl.insertArticle(article)
        }
    }

    suspend fun addArticleFav(article: Article)  {
        articleDaoRoomImpl.insertArticle(article)
    }

    suspend fun deleteArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        when (type){
            DaoType.MEMORY -> articleDaoMemoryImpl.deleteArticle(article)
            else -> articleDaoRoomImpl.deleteArticle(article)
        }
        articleDaoRoomImpl.deleteArticle(article)
    }

    fun getArticleFav(id: Long) : Article?{
        return articleDaoRoomImpl.findById(id)
    }


  suspend fun getAllArticles(type: DaoType = DaoType.MEMORY): List<Article> {
      return when (type) {
          DaoType.MEMORY -> articleDaoMemoryImpl.getAllArticles()
          else -> articleDaoRoomImpl.getAllArticles()
      }

    }


}
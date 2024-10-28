package com.example.enishop.Dao

import com.example.enishop.Dao.memory.ArticleDaoMemoryImpl

abstract class DaoFactory {
    abstract fun createArticleDao(): ArticleDao

    companion object {
        fun createArticleDao(daoType: DaoType): ArticleDao {
            return when (daoType) {
                DaoType.MEMORY -> ArticleDaoMemoryImpl()
                DaoType.NETWORK -> TODO()


            }
        }
    }

}
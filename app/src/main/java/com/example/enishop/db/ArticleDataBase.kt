package com.example.enishop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enishop.BO.Article
import com.example.enishop.Dao.ArticleDao

@Database(entities = [Article::class], version = 3)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao (): ArticleDao

    companion object {

        private var instance: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {

            if (instance == null) {

                instance = Room.databaseBuilder(
                    context = context,
                    klass = ArticleDatabase::class.java,
                    name = "tree.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as ArticleDatabase
        }
    }

}
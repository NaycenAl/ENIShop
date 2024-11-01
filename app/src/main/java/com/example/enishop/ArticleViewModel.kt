package com.example.enishop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishop.BO.Article
import com.example.enishop.Dao.DaoFactory
import com.example.enishop.Dao.DaoType
import com.example.enishop.db.ArticleDatabase
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.service.ArticleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel (private val articleRepository : ArticleRepository ) : ViewModel() {

    private  val _categories= MutableStateFlow<List<String>>(emptyList())
    var categories : StateFlow<List<String>> = _categories


    private  val _selectedCategory= MutableStateFlow<String>("")
    var selectedCategory : MutableStateFlow<String> = _selectedCategory


    private  val _articles= MutableStateFlow<List<Article>>(emptyList())
    var articles : StateFlow<List<Article>> = _articles



    init
    {
        fetchArticles()
        fetchCategories()

    }

    private fun fetchArticles() {
        viewModelScope.launch(Dispatchers.IO) {

            _articles.value = articleRepository.getAllArticlesFromAPI()
            Log.i("API Response", "Réponse reçue : $_articles")
        }
    }
    private fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = articleRepository.getCategories()

        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                return ArticleViewModel(
                    ArticleRepository(ArticleDatabase.getInstance(application.applicationContext).getArticleDao(),
                        DaoFactory.createArticleDao(DaoType.MEMORY), ArticleService.articleApi.retrofitService)

                ) as T
            }
        }
    }

}
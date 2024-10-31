package com.example.enishop


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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private var _currentArticle = MutableStateFlow<Article?>(null)
    var currentArticle : MutableStateFlow<Article?> = _currentArticle

    fun fetchArticleDetail(articleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentArticle.value = articleRepository.getArticleByIdFromAPI(articleId)
        }
    }

    //Récuperation de l'article depuis la bdd afin de savoir s'il est dans nos favoris
    private val _isArticleFavorite = MutableStateFlow(false)
    var isArticleFavorite=  _isArticleFavorite.asStateFlow()

    fun getArticleFav (id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val article = articleRepository.getArticleFav(id)
            if (article != null) {
                _isArticleFavorite.value = true
            }
        }
    }

    fun saveArticleAsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
          _currentArticle.value?.let { articleRepository.addArticleFav(it) }
            _isArticleFavorite.value = true
        }
    }

    fun deleteArticleAsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            _currentArticle.value?.let { articleRepository.deleteArticle(it) }
            _isArticleFavorite.value = false
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

                return ArticleDetailViewModel(
                    ArticleRepository(
                        ArticleDatabase.getInstance(application.applicationContext).getArticleDao(),
                        DaoFactory.createArticleDao(DaoType.MEMORY),  ArticleService.articleApi.retrofitService)
                ) as T
            }
        }

    }
}
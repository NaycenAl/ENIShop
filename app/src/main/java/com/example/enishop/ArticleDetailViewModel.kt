package com.example.enishop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishop.BO.Article
import com.example.enishop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleDetailViewModel (private val articleRepository : ArticleRepository) : ViewModel() {

    private var _article = MutableStateFlow<Article?>(null)
    var article : MutableStateFlow<Article?> = _article

    fun fetchArticleDetail(articleId: Long) {
        _article.value = articleRepository.getArticleById(articleId)
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ArticleDetailViewModel(
                    ArticleRepository()
                ) as T
            }
        }

    }
}
package com.example.enishop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishop.BO.Article
import com.example.enishop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleViewModel (articleRepository : ArticleRepository ) : ViewModel() {

    private  val _categories= MutableStateFlow<List<String>>(emptyList())
    var categories : StateFlow<List<String>> = _categories


    private  val _selectedCategory= MutableStateFlow<String>("")
    var selectedCategory : MutableStateFlow<String> = _selectedCategory


    private  val _articles= MutableStateFlow<List<Article>>(emptyList())
    var articles : StateFlow<List<Article>> = _articles

    init
    {
        _articles.value = articleRepository.getAllArticles()
        _categories.value = listOf("electronics","jewelery","men's clothing","women's clothing")

    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ArticleViewModel(
                    ArticleRepository()
                ) as T
            }
        }
    }

}
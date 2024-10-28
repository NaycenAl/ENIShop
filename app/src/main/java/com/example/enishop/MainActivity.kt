package com.example.enishop

import AppBarShop
import ArticleItem
import CatgeoriesBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enishop.Dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.ui.theme.ENIShopTheme
import com.example.enishop.ui.theme.screen.AddArticle


class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ENIShopTheme {
                Column(modifier = Modifier.fillMaxSize(),) {
                    AppBarShop()

                    Spacer(modifier = Modifier.height(12.dp))

                    Scaffold { innerPadding ->
                      //  AddArticle(modifier = Modifier.padding(innerPadding))

                      //  ArticleDetailScreen(1, ArticleDaoMemoryImpl()  , modifier = Modifier.padding(innerPadding))



                        ArticleListScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleListScreen(articleViewModel: ArticleViewModel = viewModel(factory = ArticleViewModel.Factory ),  modifier : Modifier = Modifier ) {

    val articles by articleViewModel.articles.collectAsState()
    val categories by articleViewModel.categories.collectAsState()
    var category by rememberSaveable { mutableStateOf("") }
    var filtredArticles= if (category != "")
    {
        articles.filter { it.category == category }
    } else {
        articles
    }

Column(modifier = Modifier.padding(8.dp)) {
    CatgeoriesBar(
        categories = categories ,
        selectedCategory = category,
        onCategoryChange = { category = it })
    LazyVerticalGrid(columns = GridCells.Fixed(2),) {
        items(filtredArticles) {
            ArticleItem(article = it)
        }
    }
}

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ENIShopTheme {
    AppBarShop()
    }
}
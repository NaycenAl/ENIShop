package com.example.enishop

import AppBarShop
import ArticleDetailScreen
import ArticleItem
import ArticleListBottomBar
import CatgeoriesBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.enishop.ui.theme.ENIShopTheme
import com.example.enishop.ArticleListScreen
import com.example.enishop.datastore.DataStoreManager
import com.example.enishop.ui.theme.common.FAB
import com.example.enishop.ui.theme.screen.AddArticle
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
           var isDarkModeActivated  by rememberSaveable {
                mutableStateOf(false)
            }

            LaunchedEffect(Unit) {
                DataStoreManager.isDarkModeActivated(this@MainActivity).collect {
                    isDarkModeActivated = it
                }
            }

            ENIShopTheme(darkTheme = isDarkModeActivated) {
                EniShopAppNavHost()

            }
        }
    }
}


@Composable
fun EniShopAppNavHost(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AppBarShop(
                onClickToBack =  {
            if (navHostController.previousBackStackEntry != null) {
                navHostController.popBackStack()
            }
                 })},
        floatingActionButton = { FAB( onClickToAddForm = {navHostController.navigate(AddArticleScreen.route)}) } // Add FAB with navigation
      ,  bottomBar = { ArticleListBottomBar() }
    ) { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = ArticleListScreen.route,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(route = ArticleListScreen.route) {
                ArticleListScreen(OnClickToDetails = { navHostController.navigate("${ArticleDetailsScreen.route}/$it") })
            }
            composable(route = ArticleDetailsScreen.routeWithArgs, arguments = ArticleDetailsScreen.arguments) { navBackStackEntry ->
                val articleId = navBackStackEntry.arguments?.getLong(ArticleDetailsScreen.argArticleId.toString())
                if (articleId != null) {
                    ArticleDetailScreen(articleId = articleId)
                }
            }
            composable(route = AddArticleScreen.route) {
                AddArticle(modifier)
            }

        }
    }

}


    @Composable
    fun ArticleListScreen(
        articleViewModel: ArticleViewModel = viewModel(factory = ArticleViewModel.Factory),
        modifier: Modifier = Modifier,
        OnClickToDetails: (Long) -> Unit
    ) {

        val articles by articleViewModel.articles.collectAsState()
        val categories by articleViewModel.categories.collectAsState()
        var category by rememberSaveable { mutableStateOf("") }
        var filtredArticles = if (category != "") {
            articles.filter { it.category == category }
        } else {
            articles
        }

        Column(modifier = Modifier.padding(8.dp)) {
            CatgeoriesBar(
                categories = categories,
                selectedCategory = category,
                onCategoryChange = { category = it })
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(filtredArticles) {

                    ArticleItem(article = it, onClickToDetails = OnClickToDetails)
                }

            }


        }

    }

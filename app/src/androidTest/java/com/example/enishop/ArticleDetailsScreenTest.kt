package com.example.enishop

import ArticleDetailScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.enishop.BO.Article
import com.example.enishop.Dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.repository.ArticleRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.reflect.Modifier
import java.util.Date

@RunWith(AndroidJUnit4::class)
class ArticleDetailsScreenTest() {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testArticleDetailsScreen() {

        composeTestRule.setContent {
            ArticleDetailScreen(articleId= 1, modifier = androidx.compose.ui.Modifier)

        }

        composeTestRule.onNodeWithTag("ArticleName").assertExists().assertTextEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")

    }

}
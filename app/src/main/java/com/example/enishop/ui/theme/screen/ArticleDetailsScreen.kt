import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.enishop.ArticleDetailViewModel
import kotlin.math.log

@SuppressLint("SuspiciousIndentation")
@Composable
fun ArticleDetailScreen(articleId: Long, articleDetailsViewModel: ArticleDetailViewModel= viewModel(factory = ArticleDetailViewModel.Factory )) {
    val article by articleDetailsViewModel.currentArticle.collectAsState()
    val isArticleFavorite by articleDetailsViewModel.isArticleFavorite.collectAsState()

    val context = LocalContext.current


    LaunchedEffect(articleId) {
        articleDetailsViewModel.fetchArticleDetail(articleId)
        articleDetailsViewModel.getArticleFav(articleId)

    }

    if (article != null) {

        Box(modifier = Modifier.verticalScroll(rememberScrollState())){

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = if (isArticleFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Ajouter aux favoris",
                        tint = if (isArticleFavorite) Color.Red else Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {

                                if (!isArticleFavorite) {
                                    articleDetailsViewModel.saveArticleAsFavorite()
                                } else
                                    articleDetailsViewModel.deleteArticleAsFavorite()

                            }
                    )
                    Image(
                        painter = rememberAsyncImagePainter(article!!.urlImage),
                        contentDescription = article!!.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),

                        )

                    Text(
                        text = article!!.name,

                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                var intent = Intent(Intent.ACTION_WEB_SEARCH)
                                intent.putExtra(SearchManager.QUERY, article!!.name)
                                context.startActivity(intent)
                            }
                            .testTag("ArticleName")

                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(22.dp),
                    )




                    Text(
                        text = article!!.description,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(22.dp),
                    )


                    Text(
                        text = "${article!!.price}€",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                }
            }
        }
    } else {
        Text(
            text = "Article not found",
            fontSize = 18.sp,
            color = Color.Red,

        )
    }
}

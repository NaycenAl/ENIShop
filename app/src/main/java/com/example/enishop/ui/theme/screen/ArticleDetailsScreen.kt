import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.enishop.Dao.memory.ArticleDaoMemoryImpl

@Composable
fun ArticleDetailScreen(articleId: Long, articleDao: ArticleDaoMemoryImpl, modifier: androidx.compose.ui.Modifier) {
    val article = articleDao.findById(articleId)

    if (article != null) {

        Card( modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {

                Image(
                    painter = rememberAsyncImagePainter(article.urlImage),
                    contentDescription = article.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),

                )

                Text(
                    text = article.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                    )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(22.dp),
                )




                Text(
                    text = article.description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(22.dp),
                )


                Text(
                    text = "£${article.price}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

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

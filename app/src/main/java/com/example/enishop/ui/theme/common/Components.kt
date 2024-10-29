import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.enishop.R
import com.example.enishop.ui.theme.common.FAB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarShop(onClickToBack: () -> Unit = {}, modifier: Modifier = Modifier) {
    TopAppBar(

        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {


                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Icône",
                    modifier = Modifier.size(60.dp),


                    )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "ENI-SHOP",
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))


            }
        },
        navigationIcon = {


                IconButton(onClick = { onClickToBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour",
                        tint = Color(0xFF367DDC)
                    )
                }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = MaterialTheme.shapes.small)
            .background(Color.White)

    )



}
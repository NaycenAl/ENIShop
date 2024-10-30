import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.enishop.R
import com.example.enishop.datastore.DataStoreManager
import com.example.enishop.ui.theme.common.FAB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarShop(onClickToBack: () -> Unit = {}, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable()
    { mutableStateOf(false) }

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
        actions = {
            SettingsMenu()
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
@Composable
fun SettingsMenu() {


    val context = LocalContext.current
    var checked by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        DataStoreManager.isDarkModeActivated(context).collect{
            checked = it
        }
    }


    val coroutine= rememberCoroutineScope()


    var valtext = if (checked) "Dark Mood" else "Light Mood"
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "menu",
                tint = Color(0xFF367DDC)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
           DropdownMenuItem(text = { Text(valtext, fontSize = 16.sp) },
               onClick = {
                      },
               trailingIcon = {
               Switch(
                   checked = checked,
                   onCheckedChange = { checked = it
                       coroutine.launch(Dispatchers.IO) {
                           DataStoreManager.setDarkMode(context, checked ) }
                   },
                   colors = SwitchDefaults.colors(
                       checkedThumbColor = Color.Black,
                       uncheckedThumbColor = Color.DarkGray,
                       checkedTrackColor = Color.DarkGray,
                       uncheckedTrackColor = Color.LightGray
                   )
               )
           })

        }
    }
}
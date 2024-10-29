import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CatgeoriesBar(categories : List<String>,
                  selectedCategory : String,
                  onCategoryChange : (String) -> Unit){


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) {

            FilterChip(
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Transparent
                ),
                border =   BorderStroke(
                        width = 2.dp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF144A7F), Color(0xFF367DDC))
                        )

                    ),

                shape = RoundedCornerShape(12.dp),
                selected = selectedCategory == it,
                onClick = {
                    if (selectedCategory != it) {
                    onCategoryChange(it)
                } else {
                    onCategoryChange("")
                }
                   },

                label ={ Text(text = it) },


            )
        }
    }

}

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                selected = selectedCategory == it,
                onClick = {
                    if (selectedCategory != it) {
                    onCategoryChange(it)
                } else {
                    onCategoryChange("")
                }
                   },

                        label ={ Text(text = it) }
            )
        }
    }

}



@Composable
fun CategoryChip(category : String,
                 onCategorySelected : (String) -> Unit,
                 isSelected : Boolean = false) {
    val backgroundColor = if (isSelected) Color.Blue else Color.Gray
    val textColor = if (isSelected) Color.White else Color.Black


    Box(modifier = androidx.compose.ui.Modifier
        .clip(RoundedCornerShape(15.dp))
        .background(color = backgroundColor)
      ){
        Text(
            text = category,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium)
    }
}
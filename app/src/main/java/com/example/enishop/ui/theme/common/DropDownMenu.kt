package com.example.enishop.ui.theme.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CategoryDropdownMenu(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    val categories = listOf("electronics","jewelery","men's clothing","women's clothing")


    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(

            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text("Catégories") },


                    modifier = androidx.compose.ui.Modifier
                        .menuAnchor()
                    .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(12.dp),

            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,

            ),
        )


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }

        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = { Text(category) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}
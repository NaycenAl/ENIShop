package com.example.enishop.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enishop.Dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.ui.theme.common.CategoryDropdownMenu
import com.example.enishop.ui.theme.common.TextFieldCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArticle(modifier: androidx.compose.ui.Modifier) {
    val articleDao = ArticleDaoMemoryImpl()

    var titre by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    var prix by rememberSaveable {
        mutableStateOf("")
    }
    var categorie by rememberSaveable {
        mutableStateOf("")
    }
    var showDropdown by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column (modifier = Modifier
        .padding(19.dp)

        .verticalScroll(rememberScrollState())
    ){
    Text(text = "Ajouter un article", fontSize = 22.sp, color = Color.Black, textAlign = TextAlign.Center)

        TextFieldCustom(
            value = titre,
            onValueChange = { titre = it },
            label = "Titre",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldCustom(
            value = description,
            onValueChange = { description = it },
            label = "Description",
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextFieldCustom(
            value = prix,

            onValueChange = { prix = if(it.toDoubleOrNull() != null && it.isNotEmpty()) it else  "" },
            label = "Prix",
            keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        CategoryDropdownMenu(selectedCategory = categorie,  onCategorySelected = { category ->
            categorie = category
        })


            Button(
                onClick = {
                    Toast.makeText(context, "$titre Ajouté", Toast.LENGTH_SHORT).show()

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                   ,

                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor =Color(0xFF144A7F),
                    contentColor = MaterialTheme.colorScheme.onPrimary

                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Ajouter", fontSize = 16.sp)
            }
        }

}
package com.example.enishop.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ){


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
        
        ElevatedButton(onClick = {
            Toast.makeText( context, "$titre Ajouté",Toast.LENGTH_SHORT).show()
        },   modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                focusedElevation = 4.dp,
                hoveredElevation = 4.dp,
                disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(8.dp)) {
            Text(text = "Ajouter")

        }
        


    }
}
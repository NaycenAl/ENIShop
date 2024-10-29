package com.example.enishop.ui.theme.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.enishop.AddArticleScreen
@Composable
fun FAB(onClickToAddForm: () -> Unit = {}) {
    FloatingActionButton(
        onClick = { onClickToAddForm() },
        shape = RoundedCornerShape(50.dp),
        containerColor = Color(0xFF367DDC),
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Ajouter article",
                tint = androidx.compose.ui.graphics.Color.White
            )

        }
        }


}
package com.example.enishop.ui.theme.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "TextField",
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true ){

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Gray) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,

        ),
        keyboardOptions =  keyboardOptions ,

        shape = RoundedCornerShape(12.dp),
        singleLine = singleLine,
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(16.dp)

    )



}

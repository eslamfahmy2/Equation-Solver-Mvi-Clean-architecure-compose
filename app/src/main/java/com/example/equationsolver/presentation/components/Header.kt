package com.example.equationsolver.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Header(
    title: String,
    navigateBack: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp,
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            IconButton(
                onClick = { navigateBack() },
                modifier = Modifier.align(CenterVertically)
            ) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Localized description")
            }

            Text(
                text = title,
                modifier = Modifier.padding(12.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )

        }

    }

}
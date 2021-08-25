package com.example.equationsolver.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.equationsolver.domain.ResultModel


@ExperimentalMaterialApi
@Composable
fun ResultCard(
    resultModel: ResultModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = resultModel.result,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.9f),
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface
                ),
            )

            Text(
                text = resultModel.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp
                ),
            )

        }

    }
}
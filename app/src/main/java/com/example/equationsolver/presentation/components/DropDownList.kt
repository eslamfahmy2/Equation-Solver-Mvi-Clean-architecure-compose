package com.example.equationsolver.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.equationsolver.data.engine.Operator

@Composable
fun DropDownList(
    modifier: Modifier = Modifier,
    selected: (String) -> Unit
) {

    val text = remember {
        selected(Operator.SUM.toString())
        mutableStateOf(Operator.SUM.toString())
    } // initial value
    val isOpen = remember { mutableStateOf(true) } // initial value

    val list = remember {
        Operator.values().map {
            it.toString()
        }.toList()
    }

    Box(modifier = modifier.clickable { isOpen.value = true  }) {


    Text(
        text = text.value,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.onSurface,
        fontSize = 14.sp,

    )


    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = isOpen.value,
        onDismissRequest = { isOpen.value = false },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    isOpen.value = false
                    text.value = it
                    selected(it)
                }
            ) {
                Text(it, modifier = Modifier.wrapContentWidth())
            }
        }
    }

    }
}
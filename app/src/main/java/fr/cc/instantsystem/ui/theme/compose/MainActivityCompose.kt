package fr.cc.instantsystem.ui.theme.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.cc.instantsystem.models.CategoryItemsByName


@Composable
fun initLoadingScreen(innerPaddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue,
            strokeWidth = 4.dp,
            modifier = Modifier.size(50.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun initCategoriesUi(categories: List<CategoryItemsByName>) {
    var expandedCategories by remember { mutableStateOf(false) }
    var selectedItemCategories by remember { mutableStateOf(categories[0].category) }

    var expandedSubCategories by remember { mutableStateOf(false) }
    var subItemCategories by remember { mutableStateOf(categories[0].subItems) }
    var selectedSubItemCategories by remember { mutableStateOf(categories[0].subItems[0]) }


    LazyColumn() {
        item {

            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = expandedCategories,
                onExpandedChange = { expandedCategories = !expandedCategories }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor(),
                    readOnly = true,
                    value = selectedItemCategories,
                    onValueChange = {},
                    label = { Text("Select an option") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategories)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = expandedCategories,
                    onDismissRequest = { expandedCategories = false }
                ) {
                    categories.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.category) },
                            onClick = {
                                selectedItemCategories = item.category
                                expandedCategories = false
                            }
                        )
                    }
                }
            }
        }

        item {
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = expandedSubCategories,
                onExpandedChange = { expandedSubCategories = !expandedSubCategories }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor(),
                    readOnly = true,
                    value = selectedSubItemCategories.description,
                    onValueChange = {},
                    label = { Text("Select an option") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategories)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = expandedSubCategories,
                    onDismissRequest = { expandedSubCategories = false }
                ) {
                    subItemCategories.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.category) },
                            onClick = {
                                selectedSubItemCategories = item
                                expandedCategories = false
                            }
                        )
                    }
                }
            }
        }
    }
}




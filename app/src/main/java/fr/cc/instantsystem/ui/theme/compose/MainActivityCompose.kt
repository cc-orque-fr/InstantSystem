package fr.cc.instantsystem.ui.theme.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
    LazyColumn() {
        item {

        }
    }
}
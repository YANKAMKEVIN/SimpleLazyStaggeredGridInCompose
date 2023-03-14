package com.kevin.simplelazystaggeredgridcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plcoding.simplelazystaggeredgridcompose.ui.theme.LazyStaggeredGridComposeTheme
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create a list of ListItem elements
        val items = (1..100).map {
            // For each element in the range from 1 to 100, create a ListItem
            ListItem(
                // Set the element's height randomly between 100 and 300 pixels
                height = Random.nextInt(100, 300).dp,
                // Set the element's color randomly, with an alpha value of 1.0 (opaque)
                color = Color(
                    Random.nextLong(0xFFFFFFFF)
                ).copy(alpha = 1f)
            )
        }
// Define the main UI of the application
        setContent {
            // Set the theme of the UI to LazyStaggeredGridComposeTheme
            LazyStaggeredGridComposeTheme {
                // Create a LazyVerticalStaggeredGrid that displays a list of items
                LazyVerticalStaggeredGrid(
                    // Set the number of columns in the grid to adapt to the available width of the screen with a minimum width of 30dp
                    columns = StaggeredGridCells.Adaptive(30.dp),
                    // Set the modifier of the grid to fill the maximum available space
                    modifier = Modifier.fillMaxSize(),
                    // Set the content padding of the grid to 16dp
                    contentPadding = PaddingValues(16.dp),
                    // Set the horizontal arrangement of the items in the grid to spacedBy 16dp
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    // Set the vertical arrangement of the items in the grid to spacedBy 16dp
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Add the items to the grid using the RandomColorBox composable function
                    items(items) { item ->
                        RandomColorBox(item = item)
                    }
                }
            }
        }

    }
}

data class ListItem(
    val height: Dp,
    val color: Color
)
// A composable function that creates a box with a random color, height, and rounded corners
@Composable
fun RandomColorBox(item: ListItem) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(item.height)
        .clip(RoundedCornerShape(10.dp))
        .background(item.color))
}
package com.example.bookshelf.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BookshelfApp() {
    val navController = rememberNavController()
    Scaffold(
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "book_search"
            ) {
                composable("book_search") { BookSearchScreen() }
            }
        }
    )
}

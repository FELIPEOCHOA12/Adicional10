package com.example.bookshelf.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.data.Book
import com.example.bookshelf.data.NetworkModule
import kotlinx.coroutines.launch

@Composable
fun BookSearchScreen() {
    var query by remember { mutableStateOf("") }
    val books = remember { mutableStateListOf<Book>() }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search for books") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Solución con Row y Arrangement.End para alinear el botón a la derecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val response = NetworkModule.retrofitService.searchBooks(query)
                        if (response.isSuccessful) {
                            books.clear()
                            books.addAll(response.body()?.books ?: emptyList())
                        }
                    }
                }
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(books) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = book.volumeInfo.title, style = MaterialTheme.typography.titleMedium)
        Text(text = "Author: ${book.volumeInfo.authors?.joinToString() ?: "Unknown"}")
        Text(text = book.volumeInfo.description ?: "No description available")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

package com.lavender.readmore.screens.booklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.UUID

@Composable
fun BookListScreen(
    viewModel: BookListModel = hiltViewModel(),
    onBookData: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Column(
                modifier = Modifier.padding(25.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                FloatingActionButton(
                    onClick = { onBookData(UUID.randomUUID().toString()) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add new book"
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(uiState.bookList) {
                Text(it.uuid)
                Text(it.name)
                Text(it.pageCount.toString())
            }
        }
    }
}
package com.lavender.readmore.screens.booklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                Row(
                    modifier = Modifier.clickable { onBookData(it.uuid) }
                ) {
                    Column {
                        Text(text = it.uuid)
                        Text(it.name)

                        if (it.active && it.pageCount > 0) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(16.dp),
                                progress = {
                                    it.currentPage.toFloat() / it.pageCount
                                },
                            )
                        }

                        HorizontalDivider(thickness = 2.dp)
                    }
                }

            }
        }
    }
}
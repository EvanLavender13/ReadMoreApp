package com.lavender.readmore.screens.bookdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.Instant

@Composable
fun BookDataScreen(
    bookDataViewModel: BookDataModel = hiltViewModel(),
    bookSessionViewModel: BookSessionDataModel = hiltViewModel(),
    uuid: String,
    onBack: () -> Unit,
) {
    val bookDataState by bookDataViewModel.uiState.collectAsState()
    val bookSessionState by bookSessionViewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingActionButton(
                    onClick = bookDataViewModel::recordSession
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Record Session"
                    )
                }

                FloatingActionButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        bookDataViewModel.saveBookData(uuid)
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Save"
                    )
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Row {
                OutlinedTextField(
                    modifier = Modifier.weight(1.0f),
                    value = bookDataState.name,
                    label = { Text(text = "Name") },
                    onValueChange = bookDataViewModel::updateName
                )
            }

            Row {
                OutlinedTextField(
                    modifier = Modifier.weight(1.0f),
                    value = bookDataState.author,
                    label = { Text(text = "Author") },
                    onValueChange = bookDataViewModel::updateAuthor
                )
            }

            Row {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = bookDataState.currentPage.toString(),
                    label = { Text(text = "Current Page") },
                    onValueChange = bookDataViewModel::updateCurrentPage
                )

                Spacer(
                    modifier = Modifier.padding(4.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = bookDataState.pageCount.toString(),
                    label = { Text(text = "Page Count") },
                    onValueChange = bookDataViewModel::updatePageCount
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Active")

                Spacer(
                    modifier = Modifier.padding(4.dp)
                )

                Switch(
                    checked = bookDataState.active,
                    onCheckedChange = bookDataViewModel::setActive
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reading Sessions",
                    fontSize = 16.sp
                )
            }

            LazyColumn(modifier = Modifier.padding(padding)) {
                items(bookSessionState.bookSessionList) {
                    Text("${it.fromPage} to ${it.toPage}")
                    Text(Instant.ofEpochMilli(it.date).toString())
                    HorizontalDivider(thickness = 2.dp)
                }
            }
        }
    }
}
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookDataScreen(
    viewModel: BookDataModel = hiltViewModel(),
    uuid: String,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Column(
                modifier = Modifier.padding(25.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                FloatingActionButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        viewModel.saveBookData(uuid)
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
                TextField(
                    modifier = Modifier.weight(1.0f),
                    value = uiState.name,
                    label = { Text(text = "Name") },
                    onValueChange = viewModel::updateName
                )
            }

            Row {
                TextField(
                    modifier = Modifier.weight(1.0f),
                    value = uiState.pageCount.toString(),
                    label = { Text(text = "Page Count") },
                    onValueChange = viewModel::updatePageCount
                )
            }

            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Active")

                Spacer(
                    modifier = Modifier.padding(4.dp)
                )

                Switch(
                    checked = uiState.active,
                    onCheckedChange = viewModel::setActive
                )
            }

            Row (
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reading Sessions",
                    fontSize = 16.sp)
            }
        }

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(uiState.bookSessionList) {
                Text("A")
            }
        }
    }
}
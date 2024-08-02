package com.lavender.readmore

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.lavender.readmore.data.BookListModel

@Composable
fun BookListScreen(
    viewModel: BookListModel = hiltViewModel()
) {
    val tag = "BookListScreen"

    val uiState by viewModel.uiState.collectAsState()
    Log.d(tag, "uiState size: ${uiState.bookList.size}")

    Text(
        text = "Hello"
    )
}
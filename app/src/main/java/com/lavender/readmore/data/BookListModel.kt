package com.lavender.readmore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class BookListState(
    val bookList: List<BookData> = listOf()
)

@HiltViewModel
class BookListModel @Inject constructor(
    private val bookRepository: BookDataRepository
) : ViewModel() {
    private val tag = "BookListModel"

    val uiState: StateFlow<BookListState> =
        bookRepository.getBookDataStream().map { BookListState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BookListState()
        )
}
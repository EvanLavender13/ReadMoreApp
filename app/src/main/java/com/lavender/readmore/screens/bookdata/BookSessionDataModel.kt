package com.lavender.readmore.screens.bookdata

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lavender.readmore.model.booksession.BookSessionData
import com.lavender.readmore.model.booksession.BookSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class BookSessionState(
    val bookSessionList: List<BookSessionData> = listOf()
)

@HiltViewModel
class BookSessionDataModel @Inject constructor(
    private val bookSessionRepository: BookSessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val tag = "BookSessionDataModel"

    private val _uuid: String? = savedStateHandle["uuid"]
    private var uuid: String = "default-uuid"

    init {
        _uuid?.let { uuid = _uuid }
    }

    val uiState: StateFlow<BookSessionState> =
        bookSessionRepository.getBookSessionDataStream(uuid).map { BookSessionState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BookSessionState()
        )
}
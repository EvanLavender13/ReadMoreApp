package com.lavender.readmore.screens.bookdata

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lavender.readmore.model.bookdata.BookData
import com.lavender.readmore.model.bookdata.BookDataRepository
import com.lavender.readmore.model.booksession.BookSessionData
import com.lavender.readmore.model.booksession.BookSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookDataState(
    val name: String = "default-name",
    val pageCount: Int = 0,
    val active: Boolean = false,
    val currentPage: Int = 0,

    val bookSessionList: List<BookSessionData> = listOf()
)

@HiltViewModel
class BookDataModel @Inject constructor(
    private val bookRepository: BookDataRepository,
    private val bookSessionRepository: BookSessionRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val tag = "BookDataModel"

    private val _uiState = MutableStateFlow(BookDataState())
    val uiState = _uiState.asStateFlow()
    val uuid: String? = savedStateHandle["uuid"]

    init {
        uuid?.let {
            loadBookData(uuid)
        }
    }

    fun saveBookData(uuid: String) {
        Log.d(tag, "saveBookData: ${uiState.value}")
        viewModelScope.launch {
            bookRepository.saveBookData(
                BookData(
                    uuid = uuid,
                    name = uiState.value.name,
                    pageCount = uiState.value.pageCount,
                    active = uiState.value.active,
                    currentPage = uiState.value.currentPage
                )
            )
        }
    }

    private fun loadBookData(uuid: String) {
        Log.d(tag, "loadBookData: $uuid")
        viewModelScope.launch {
            bookRepository.getBookDataById(uuid)?.let { bookData ->
                Log.d(tag, "loadBookData found: $bookData")
                _uiState.update {
                    it.copy(
                        name = bookData.name,
                        pageCount = bookData.pageCount,
                        active = bookData.active,
                        currentPage = bookData.currentPage
                    )
                }
            }

            bookSessionRepository.getBookSessionDataStream(uuid).map { bookSessionList ->
                Log.d(tag, "loadBookData sessions: ${bookSessionList.size}")
                _uiState.update {
                    it.copy(bookSessionList = bookSessionList)
                }
            }
        }
    }

    fun updateName(name: String) {
        Log.d(tag, "updateName: $name")
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updatePageCount(pageCountStr: String) {
        Log.d(tag, "updatePageCount: $pageCountStr")

        var pageCount = 0
        pageCountStr.toIntOrNull()?.let { pageCount = it }

        _uiState.update {
            it.copy(pageCount = pageCount)
        }
    }

    fun setActive(active: Boolean) {
        Log.d(tag, "setActive: $active")

        _uiState.update {
            it.copy(active = active)
        }
    }
}
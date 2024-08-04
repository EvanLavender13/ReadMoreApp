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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.measureTime

data class BookDataState(
    val name: String = "default-name",
    val author: String = "default-author",
    val pageCount: Int = 0,
    val active: Boolean = false,
    val currentPage: Int = 1,
)

@HiltViewModel
class BookDataModel @Inject constructor(
    private val bookRepository: BookDataRepository,
    private val bookSessionRepository: BookSessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val tag = "BookDataModel"

    private val _uiState = MutableStateFlow(BookDataState())
    val uiState = _uiState.asStateFlow()
    private val _uuid: String? = savedStateHandle["uuid"]
    private var uuid: String = "default-uuid"
    private var currentPage: Int = 0

    init {
        _uuid?.let {
            uuid = _uuid
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
                    author = uiState.value.author,
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
                        author = bookData.author,
                        pageCount = bookData.pageCount,
                        active = bookData.active,
                        currentPage = bookData.currentPage
                    )
                }

                currentPage = bookData.currentPage
            }
        }
    }

    fun recordSession() {
        val totalPageCount = uiState.value.pageCount
        val newPage = uiState.value.currentPage

        Log.d(
            tag,
            "recordSession currentPage: $currentPage, newPage: $newPage, totalPageCount: $totalPageCount"
        )

        if (newPage > currentPage) {
            val pageCount = newPage - currentPage
            Log.d(tag, "recordSession pages read: $pageCount")

            viewModelScope.launch {
                bookSessionRepository.saveBookSessionData(
                    BookSessionData(
                        uuid = UUID.randomUUID().toString(),
                        bookId = uuid,
                        date = System.currentTimeMillis(),
                        fromPage = currentPage,
                        toPage = newPage
                    )
                )

                currentPage = newPage
                saveBookData(uuid)
            }
        } else {
            Log.d(tag, "recordSession no session recorded: newPage <= currentPage")
        }
    }

    fun updateName(name: String) {
        Log.d(tag, "updateName: $name")
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updateAuthor(author: String) {
        Log.d(tag, "updateAuthor: $author")
        _uiState.update {
            it.copy(author = author)
        }
    }

    fun updatePageCount(pageCountStr: String) {
        Log.d(tag, "updatePageCount: $pageCountStr")

        var pageCount = uiState.value.pageCount
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

    fun updateCurrentPage(currentPageStr: String) {
        Log.d(tag, "updateCurrentPage: $currentPageStr")

        var currentPage = uiState.value.currentPage
        currentPageStr.toIntOrNull()?.let { currentPage = it }

        _uiState.update {
            it.copy(currentPage = currentPage)
        }
    }
}
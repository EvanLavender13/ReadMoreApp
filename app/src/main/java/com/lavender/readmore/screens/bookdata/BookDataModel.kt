package com.lavender.readmore.screens.bookdata

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lavender.readmore.data.BookData
import com.lavender.readmore.data.BookDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookDataState(
    val name: String = "default-name",
    val pageCount: Int = 0
)

@HiltViewModel
class BookDataModel @Inject constructor(
    private val bookRepository: BookDataRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val tag = "BookDataModel"

    val uiState = MutableStateFlow(BookDataState())

    fun saveBookData(uuid: String) {
        Log.d(tag, "saveBookData: ${uiState.value}")
        viewModelScope.launch {
            bookRepository.saveBookData(
                BookData(
                    uuid = uuid,
                    name = uiState.value.name,
                    pageCount = uiState.value.pageCount
                )
            )
        }
    }

    fun updateName(name: String) {
        Log.d(tag, "updateName: $name")
        uiState.update {
            it.copy(name = name)
        }
    }

    fun updatePageCount(pageCountStr: String) {
        Log.d(tag, "updatePageCount: $pageCountStr")

        var pageCount = 0
        pageCountStr.toIntOrNull()?.let { pageCount = it }

        uiState.update {
            it.copy(pageCount = pageCount)
        }
    }
}
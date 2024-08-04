package com.lavender.readmore.model.booksession

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookSessionRepository @Inject constructor(
    private val dataSource: BookSessionDao
) {
    private val tag = "BookSessionRepository"

    fun getBookSessionDataStream(bookId: String): Flow<List<BookSessionData>> {
        Log.d(tag, "getBookSessionDataStream: $bookId")
        return dataSource.observeAllByBookId(bookId).map { it.toData() }
    }

    suspend fun saveBookSessionData(bookSessionData: BookSessionData) {
        Log.d(tag, "saveBookSessionData: $bookSessionData")
        dataSource.upsert(bookSessionData.toEntity())
    }
}
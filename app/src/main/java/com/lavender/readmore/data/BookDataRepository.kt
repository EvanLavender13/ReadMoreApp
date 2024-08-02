package com.lavender.readmore.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookDataRepository @Inject constructor(
    private val dataSource: BookDao
) {
    private val tag = "BookDataRepository"

    fun getBookDataStream(): Flow<List<BookData>> {
        Log.d(tag, "getBookDataStream")
        return dataSource.observeAll().map { it.toData() }
    }

    suspend fun saveBookData(bookData: BookData) {
        Log.d(tag, "saveBookData $bookData")
        dataSource.upsert(bookData.toEntity())
    }
}
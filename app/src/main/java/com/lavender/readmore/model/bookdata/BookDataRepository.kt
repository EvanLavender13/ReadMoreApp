package com.lavender.readmore.model.bookdata

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
        return dataSource.observeAllSortedByActive().map { it.toData() }
    }

    suspend fun getBookDataById(uuid: String): BookData? {
        Log.d(tag, "getBookDataById: $uuid")
        return dataSource.getById(uuid)?.toData()
    }

    suspend fun saveBookData(bookData: BookData) {
        Log.d(tag, "saveBookData: $bookData")
        dataSource.upsert(bookData.toEntity())
    }
}
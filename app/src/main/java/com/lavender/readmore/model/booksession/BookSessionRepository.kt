package com.lavender.readmore.model.booksession

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookSessionRepository @Inject constructor(
    private val dataSource: BookSessionDao
) {
    private val tag = "BookSessionRepository"

    fun getBookSessionDataStream(uuid: String): Flow<List<BookSessionData>> {
        Log.d(tag, "getBookSessionDataStream: $uuid")
        return dataSource.observeAllByUuid(uuid).map { it.toData() }
    }
}
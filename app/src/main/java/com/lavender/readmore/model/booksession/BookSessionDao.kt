package com.lavender.readmore.model.booksession

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSessionDao {
    @Query("SELECT * FROM `book-session-data` WHERE bookId = :bookId")
    fun observeAllByBookId(bookId: String): Flow<List<BookSessionDataEntity>>

    @Upsert
    suspend fun upsert(bookSessionDataEntity: BookSessionDataEntity)
}
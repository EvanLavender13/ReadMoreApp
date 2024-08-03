package com.lavender.readmore.model.booksession

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSessionDao {
    @Query("SELECT * FROM `book-session-data` WHERE uuid = :uuid")
    fun observeAllByUuid(uuid: String): Flow<List<BookSessionDataEntity>>
}
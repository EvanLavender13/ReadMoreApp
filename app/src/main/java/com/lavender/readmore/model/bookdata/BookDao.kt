package com.lavender.readmore.model.bookdata

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {
    @Query("SELECT * FROM `book-data`")
    fun observeAll(): Flow<List<BookDataEntity>>

    @Query("SELECT * FROM `book-data` ORDER BY active DESC")
    fun observeAllSortedByActive(): Flow<List<BookDataEntity>>

    @Query("SELECT * FROM `book-data` WHERE uuid = :uuid")
    suspend fun getById(uuid: String): BookDataEntity?

    @Upsert
    suspend fun upsert(bookDataEntity: BookDataEntity)
}
package com.lavender.readmore.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {
    @Query("SELECT * FROM `book-data`")
    fun observeAll(): Flow<List<BookDataEntity>>

    @Query("SELECT * FROM `book-data` WHERE uuid = :uuid")
    fun observeById(uuid: String): Flow<BookDataEntity>

    @Upsert
    suspend fun upsert(bookDataEntity: BookDataEntity)
}
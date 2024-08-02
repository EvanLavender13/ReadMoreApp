package com.lavender.readmore.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Query("SELECT * FROM `book-data`")
    fun observeAll(): Flow<List<BookDataEntity>>

}
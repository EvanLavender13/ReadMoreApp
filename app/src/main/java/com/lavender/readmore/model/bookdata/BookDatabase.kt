package com.lavender.readmore.model.bookdata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDataEntity::class], version = 3, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}
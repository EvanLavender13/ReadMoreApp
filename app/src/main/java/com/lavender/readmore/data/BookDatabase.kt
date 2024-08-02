package com.lavender.readmore.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDataEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}
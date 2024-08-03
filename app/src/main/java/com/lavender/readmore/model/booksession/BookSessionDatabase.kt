package com.lavender.readmore.model.booksession

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookSessionDataEntity::class], version = 1, exportSchema = false)
abstract class BookSessionDatabase : RoomDatabase() {

    abstract fun bookSessionDao(): BookSessionDao
}
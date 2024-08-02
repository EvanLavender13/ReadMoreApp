package com.lavender.readmore.util

import android.content.Context
import androidx.room.Room
import com.lavender.readmore.data.BookDao
import com.lavender.readmore.data.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookDatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BookDatabase {
        return Room.databaseBuilder(
            context.applicationContext, BookDatabase::class.java, "book-data.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBookDao(database: BookDatabase): BookDao = database.bookDao()
}
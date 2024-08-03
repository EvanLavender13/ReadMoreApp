package com.lavender.readmore.util

import android.content.Context
import androidx.room.Room
import com.lavender.readmore.model.bookdata.BookDao
import com.lavender.readmore.model.bookdata.BookDatabase
import com.lavender.readmore.model.booksession.BookSessionDao
import com.lavender.readmore.model.booksession.BookSessionDatabase
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

@Module
@InstallIn(SingletonComponent::class)
object BookSessionDatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BookSessionDatabase {
        return Room.databaseBuilder(
            context.applicationContext, BookSessionDatabase::class.java, "book-session-data.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBookSessionDao(database: BookSessionDatabase): BookSessionDao =
        database.bookSessionDao()
}
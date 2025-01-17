package com.lavender.readmore.model.booksession

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book-session-data")
data class BookSessionDataEntity(
    @PrimaryKey val uuid: String,
    val bookId: String,
    val date: Long,
    val fromPage: Int,
    val toPage: Int
)

fun BookSessionDataEntity.toData() = BookSessionData(
    uuid = uuid,
    bookId = bookId,
    date = date,
    fromPage = fromPage,
    toPage = toPage
)

fun List<BookSessionDataEntity>.toData() = map(BookSessionDataEntity::toData)
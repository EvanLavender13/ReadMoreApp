package com.lavender.readmore.model.bookdata

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book-data")
data class BookDataEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    val pageCount: Int,
    val active: Boolean,
    val currentPage: Int
)

fun BookDataEntity.toData() = BookData(
    uuid = uuid,
    name = name,
    pageCount = pageCount,
    active = active,
    currentPage = currentPage
)

fun List<BookDataEntity>.toData() = map(BookDataEntity::toData)
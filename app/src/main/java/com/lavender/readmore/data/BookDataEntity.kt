package com.lavender.readmore.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book-data")
data class BookDataEntity(
    @PrimaryKey val uuid: String,
    val name: String
)

fun BookDataEntity.toData() = BookData(
    uuid = uuid,
    name = name
)

fun List<BookDataEntity>.toData() = map(BookDataEntity::toData)
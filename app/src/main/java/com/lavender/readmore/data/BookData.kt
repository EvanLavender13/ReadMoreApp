package com.lavender.readmore.data

data class BookData(
    val uuid: String,
    val name: String
)

fun BookData.toEntity() = BookDataEntity(
    uuid = uuid,
    name = name
)

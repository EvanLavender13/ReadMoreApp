package com.lavender.readmore.data

import java.util.UUID

data class BookData(
    val uuid: String = "default-uuid",
    val name: String = "default-name",
    val pageCount: Int = 0
)

fun BookData.toEntity() = BookDataEntity(
    uuid = uuid,
    name = name,
    pageCount = pageCount
)

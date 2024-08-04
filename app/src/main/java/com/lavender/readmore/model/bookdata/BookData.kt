package com.lavender.readmore.model.bookdata

data class BookData(
    val uuid: String = "default-uuid",
    val name: String = "default-name",
    val author: String = "default-author",
    val pageCount: Int = 0,
    val active: Boolean = false,
    val currentPage: Int = 0
)

fun BookData.toEntity() = BookDataEntity(
    uuid = uuid,
    name = name,
    author = author,
    pageCount = pageCount,
    active = active,
    currentPage = currentPage
)

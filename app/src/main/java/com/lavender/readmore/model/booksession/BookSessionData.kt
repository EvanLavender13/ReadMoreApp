package com.lavender.readmore.model.booksession

data class BookSessionData(
    val uuid: String = "default-uuid",
    val bookId: String = "default-uuid",
    // data
    val fromPage: Int = 0,
    val toPage: Int = 0
)

fun BookSessionData.toEntity() = BookSessionDataEntity(
    uuid = uuid,
    bookId = bookId,
    fromPage = fromPage,
    toPage = toPage
)
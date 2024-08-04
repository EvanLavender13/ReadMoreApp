package com.lavender.readmore.model.booksession

data class BookSessionData(
    val uuid: String = "default-uuid",
    val bookId: String = "default-uuid",
    val date: Long = 0,
    val fromPage: Int = 0,
    val toPage: Int = 0
)

fun BookSessionData.toEntity() = BookSessionDataEntity(
    uuid = uuid,
    bookId = bookId,
    date = date,
    fromPage = fromPage,
    toPage = toPage
)
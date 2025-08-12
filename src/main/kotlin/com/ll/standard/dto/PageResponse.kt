package com.ll.standard.dto

class PageResponse<T>(
    val totalElements: Long,
    val page: Int,
    val size: Int,
    val content: List<T>,
) {
    val totalPages: Int
        get() = (if (totalElements == 0L) 0 else ((totalElements -1) / size) + 1).toInt()
    val isLast: Boolean
        get() = page == totalPages
}
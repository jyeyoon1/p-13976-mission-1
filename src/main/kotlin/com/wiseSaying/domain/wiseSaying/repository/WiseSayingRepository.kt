package com.wiseSaying.domain.wiseSaying.repository

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying
import com.wiseSaying.standard.util.dto.PageResponse

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): Int
    fun isEmpty(): Boolean
    fun delete(wiseSaying: WiseSaying)
    fun findAll(): List<WiseSaying>
    fun findAll(page: Int, size: Int): PageResponse<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun clear()
    fun build()
    fun findByContentLike(keyword: String): List<WiseSaying>
    fun findByContentLike(keyword: String, page: Int, size: Int): PageResponse<WiseSaying>
    fun findByAuthorLike(keyword: String): List<WiseSaying>
    fun findByAuthorLike(keyword: String, page: Int, size: Int): PageResponse<WiseSaying>
}
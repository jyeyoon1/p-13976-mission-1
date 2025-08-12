package com.wiseSaying.domain.wiseSaying.repository

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying
import com.wiseSaying.standard.dto.PageResponse

class WiseSayingMemoryRepository : WiseSayingRepository {
    var lastId: Int = 0
    var wiseSayings: MutableList<WiseSaying> = mutableListOf()

    override fun save(wiseSaying: WiseSaying): Int {
        if(wiseSaying.isNew()) wiseSaying.id = ++lastId
        wiseSayings.add(wiseSaying)
        return lastId
    }
    override fun isEmpty(): Boolean {
        return wiseSayings.isEmpty()
    }
    override fun delete(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }
    override fun findAll(): List<WiseSaying> {
        return wiseSayings.reversed()
    }
    override fun findAll(page: Int, size: Int): PageResponse<WiseSaying> {
        val pagedContents = findAll()
            .drop((page - 1) * size)
            .take(size)

        return PageResponse<WiseSaying>(
            wiseSayings.size.toLong(),
            page,
            size,
            pagedContents
        )
    }
    override fun findById(id: Int): WiseSaying? {
        return wiseSayings.firstOrNull() { it.id == id }
    }

    override fun clear() {
        wiseSayings.clear()
    }

    override fun build() {

    }

    override fun findByContentLike(keyword: String): List<WiseSaying> {
        return wiseSayings.filter { it.content.contains(keyword) }
    }
    override fun findByContentLike(keyword: String, page: Int, size: Int): PageResponse<WiseSaying> {
        val filteredWiseSayings = findByContentLike(keyword)
        val pagedContents = filteredWiseSayings
            .drop((page -1) * size)
            .take(size)
        return PageResponse<WiseSaying>(
            filteredWiseSayings.size.toLong(),
            page,
            size,
            pagedContents

        )
    }
    override fun findByAuthorLike(keyword: String): List<WiseSaying> {
        return wiseSayings.filter { it.author.contains(keyword) }
    }
    override fun findByAuthorLike(keyword: String, page: Int, size: Int): PageResponse<WiseSaying> {
        val filteredWiseSayings = findByAuthorLike(keyword)
        val pagedContents = filteredWiseSayings
            .drop((page -1) * size)
            .take(size)
        return PageResponse<WiseSaying>(
            filteredWiseSayings.size.toLong(),
            page,
            size,
            pagedContents

        )
    }
}
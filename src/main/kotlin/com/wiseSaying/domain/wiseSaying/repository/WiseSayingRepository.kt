package com.wiseSaying.domain.wiseSaying.repository

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying

class WiseSayingRepository {

    var lastId: Int = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun add(content: String, author: String): Int {
        wiseSayings.add(WiseSaying(lastId++, content, author))
        return lastId
    }
    fun findAllOrderByIdDesc(): List<WiseSaying>? {
        return wiseSayings.reversed()
    }
    fun delete(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }
    fun update(wiseSaying: WiseSaying) {
        wiseSayings.add(wiseSaying)
    }
    fun findById(id: Int): WiseSaying? {
        return wiseSayings.firstOrNull { it.id == id }
    }
}
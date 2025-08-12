package com.wiseSaying.domain.wiseSaying.service

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying
import com.wiseSaying.domain.wiseSaying.repository.WiseSayingRepository

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingRepository()

    fun addWiseSaying(content: String, author: String): Int {
        return wiseSayingRepository.add(content, author)
    }

    fun toList(): List<WiseSaying>? {
        return wiseSayingRepository.findAllOrderByIdDesc()
    }

    fun deleteWiseSaying(wiseSaying: WiseSaying) {
        wiseSayingRepository.delete(wiseSaying)
    }

    fun modifyWiseSaying(wiseSaying: WiseSaying, content: String, author: String) {
        wiseSaying.update(content, author)
        wiseSayingRepository.update(wiseSaying)
    }

    fun getWiseSaying(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }
}
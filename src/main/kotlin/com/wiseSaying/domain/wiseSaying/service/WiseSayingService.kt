package com.wiseSaying.domain.wiseSaying.service

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying
import com.wiseSaying.domain.wiseSaying.repository.WiseSayingFileRepository

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingFileRepository()

    fun reset() {
        wiseSayingRepository.clear()
    }

    fun addWiseSaying(content: String, author: String): Int {
        val wiseSaying = WiseSaying(content, author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun toList(): List<WiseSaying>? {
        return wiseSayingRepository.findAll()
    }

    fun deleteWiseSaying(wiseSaying: WiseSaying) {
        wiseSayingRepository.delete(wiseSaying)
    }

    fun modifyWiseSaying(wiseSaying: WiseSaying, content: String, author: String) {
        wiseSaying.update(content, author)
        wiseSayingRepository.save(wiseSaying)
    }

    fun getWiseSaying(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun build() {
        wiseSayingRepository.build()
    }
}
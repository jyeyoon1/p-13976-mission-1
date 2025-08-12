package com.ll.domain.wiseSaying.service

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository
import com.ll.global.container.Container
import com.ll.standard.dto.PageResponse

class WiseSayingService {
    private val wiseSayingRepository = Container.wiseSayingRepository

    fun reset() {
        wiseSayingRepository.clear()
    }

    fun addWiseSaying(content: String, author: String): Int {
        val wiseSaying = WiseSaying(content, author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun toList(page: Int, size: Int): PageResponse<WiseSaying> {
        return wiseSayingRepository.findAll(page, size)
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

    fun search(type: String, keyword: String, page: Int, size: Int): PageResponse<WiseSaying> {
        return if(type.equals("content")) {
            wiseSayingRepository.findByContentLike(keyword, page, size)
        }else if(type.equals("author")){
            wiseSayingRepository.findByAuthorLike(keyword, page, size)
        }else{
            PageResponse<WiseSaying>(0,0,0, listOf())
        }
    }
}
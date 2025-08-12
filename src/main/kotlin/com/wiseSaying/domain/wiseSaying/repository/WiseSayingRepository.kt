package com.wiseSaying.domain.wiseSaying.repository

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): Int
    fun isEmpty(): Boolean
    fun delete(wiseSaying: WiseSaying)
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun clear()
    fun build()
}
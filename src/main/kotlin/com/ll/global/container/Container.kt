package com.ll.global.container

import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository
import com.ll.domain.wiseSaying.repository.WiseSayingMemoryRepository
import com.ll.domain.wiseSaying.repository.WiseSayingRepository
import com.ll.global.app.AppConfig

object Container {
    private val wiseSayingFileRepository by lazy { WiseSayingFileRepository() }
    private val wiseSayingMemoryRepository by lazy { WiseSayingMemoryRepository() }

    val wiseSayingRepository : WiseSayingRepository
        get() = when (AppConfig.mode) {
            "test" -> wiseSayingMemoryRepository
            else -> wiseSayingFileRepository
        }
}
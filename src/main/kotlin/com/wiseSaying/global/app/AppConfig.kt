package com.wiseSaying.global.app

object AppConfig {
    private var mode = "dev"

    fun setModeToTest() {
        mode = "test"
    }

    fun setModeToDev() {
        mode = "dev"
    }
}
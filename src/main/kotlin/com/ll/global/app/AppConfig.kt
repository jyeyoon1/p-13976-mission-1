package com.ll.global.app

object AppConfig {
    var mode = "dev"

    fun setModeToTest() {
        mode = "test"
    }

    fun setModeToDev() {
        mode = "dev"
    }
}
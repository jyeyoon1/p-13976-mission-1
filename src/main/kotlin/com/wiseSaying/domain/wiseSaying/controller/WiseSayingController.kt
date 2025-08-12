package com.wiseSaying.domain.wiseSaying.controller

import com.wiseSaying.domain.wiseSaying.service.WiseSayingService

class WiseSayingController {

    private val wiseSayingService = WiseSayingService()

    fun addWiseSaying(){
        print("명언 : ")
        val content = readlnOrNull()!!.trim()
        print("작가 : ")
        val author = readlnOrNull()!!.trim()
        val id = wiseSayingService.addWiseSaying(author, content)
        println("${id}번 명언이 등록되었습니다.")
    }

    fun toList(){
        println("번호 / 작가 / 명언")
        println("----------------------")
        val wiseSayings = wiseSayingService.toList()
        wiseSayings?.forEach {
            println("${it.id} / ${it.author} / ${it.content}")
        }
    }

    fun deleteWiseSaying(id: Int){
        val wiseSaying = wiseSayingService.getWiseSaying(id)
        if (wiseSaying == null){
            println("${id}번 명언은 존재하지 않습니다.")
        }else {
            wiseSayingService.deleteWiseSaying(wiseSaying)
            println("${id}번 명언이 삭제되었습니다.")
        }
    }

    fun modifyWiseSaying(id: Int){
        var wiseSaying = wiseSayingService.getWiseSaying(id)
        if (wiseSaying == null){
            println("${id}번 명언은 존재하지 않습니다.")
        }else {
            println("명언(기존) : ${wiseSaying.content}")
            print("명언 : ")
            val content = readlnOrNull()!!.trim()
            println("작가(기존) : ${wiseSaying.author}")
            print("작가 : ")
            val author = readlnOrNull()!!.trim()
            wiseSayingService.modifyWiseSaying(wiseSaying, content, author)
        }
    }
}

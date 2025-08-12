package com.ll.domain.wiseSaying.controller

import com.ll.domain.wiseSaying.service.WiseSayingService

class WiseSayingController {

    private val wiseSayingService = WiseSayingService()

    fun addWiseSaying(){
        print("명언 : ")
        val content = readlnOrNull()!!.trim()
        print("작가 : ")
        val author = readlnOrNull()!!.trim()
        val id = wiseSayingService.addWiseSaying(content, author)
        println("${id}번 명언이 등록되었습니다.")
    }

    fun toList(page: Int, size: Int) {
        println("번호 / 작가 / 명언")
        println("----------------------")
        val wiseSayings = wiseSayingService.toList(page, size)
        wiseSayings?.content?.forEach {
            println("${it.id} / ${it.author} / ${it.content}")
        }
        if(wiseSayings.totalElements > 0) {
            print("페이지 : ")
            for (i in 1..wiseSayings.totalPages) {
                if (i-1 == page) {
                    print("[${i}]")
                } else if (page > i-1) {
                    print("${i} / ")
                } else {
                    print(" / ${i}")
                }
            }
            println("")
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

    fun build() {
        wiseSayingService.build()
        println("data.json 파일의 내용이 갱신되었습니다.")
    }

    fun search(type:String?, keyword: String?, page: Int, size: Int) {
        println("----------------------")
        if(type == null || keyword == null){
            println("정확한 검색어를 입력해주세요.")
        }else {
            val wiseSayings = wiseSayingService.search(type, keyword, page, size)
            println("검색타입 : $type")
            println("검색어 : ${keyword}")
            println("----------------------")
            println("번호 / 작가 / 명언")
            println("----------------------")
            if(wiseSayings.totalElements > 0) {
                wiseSayings.content?.forEach {
                    println("${it.id} / ${it.author} / ${it.content}")
                }
                print("페이지 : ")
                for (i in 1..wiseSayings.totalPages) {
                    if (i-1 == page) {
                        print(" [${i}]")
                    } else if (page > i-1) {
                        print("${i} /")
                    } else {
                        print(" / ${i}")
                    }
                }
                println("")
            }
        }
    }
}

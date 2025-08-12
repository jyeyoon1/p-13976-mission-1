package com.wiseSaying

import com.wiseSaying.domain.wiseSaying.controller.WiseSayingController

class App {
    fun run() {

        val wiseSayingController = WiseSayingController()
        println("== 명언 앱 ==")
        while(true) {
            print("명령)")
            val input = readlnOrNull()!!.trim()
            val rq = Rq(input)
            when (rq.action) {
                "종료" -> break
                "등록" -> wiseSayingController.addWiseSaying()
                "목록" -> wiseSayingController.toList()
                "삭제", "수정" -> {
                    val id = rq.getParamValueAsInt("id", -1)
                    if(id == -1) {
                        println("id를 다시 입력해주세요.")
                        continue
                    }else{
                        if(rq.action == "삭제"){
                            wiseSayingController.deleteWiseSaying(id)
                        }else{
                            wiseSayingController.modifyWiseSaying(id)
                        }
                    }
                }
                "빌드" -> wiseSayingController.build()
            }
        }
    }
}
package com.ll

import com.ll.domain.system.controller.SystemController
import com.ll.domain.wiseSaying.controller.WiseSayingController
import com.ll.global.rq.Rq

class App {
    fun run() {

        val wiseSayingController = WiseSayingController()
        val systemController = SystemController()
        println("== 명언 앱 ==")
        while(true) {
            print("명령)")
            val input = readlnOrNull()!!.trim()
            val rq = Rq(input)
            when (rq.action) {
                "종료" -> {
                    systemController.shutdown()
                    break
                }
                "등록" -> wiseSayingController.addWiseSaying()
                "목록" -> {
                    val page = rq.getParamValueAsInt("page", 1) -1
                    if(rq.getKeywordAsString()==null && rq.getKeywordAsString()==null) {
                        wiseSayingController.toList(page, 5)
                    }else{
                        wiseSayingController.search(rq.getKeywordTypeAsString(), rq.getKeywordAsString(), page, 5)
                    }
                }
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
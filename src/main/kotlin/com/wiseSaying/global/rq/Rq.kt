package com.wiseSaying.global.rq

class Rq(cmd: String) {
    val action: String
    private val paramMap =  mutableMapOf<String, String>()

    init {
        val cmdBits = cmd.split("?", limit = 2)
        action = cmdBits[0].trim()
        if ( cmdBits.size == 2) {
            val queryStr = cmdBits[1]
            val queryBits = queryStr.split("&")
            for(queryBit in queryBits) {
                val queryParamBits = queryBit.split("=", limit = 2)

                if(queryParamBits.size != 2){
                    continue
                }
                val paramKey = queryParamBits[0].trim()
                val paramValue = queryParamBits[1].trim()

                paramMap[paramKey] = paramValue
            }
        }
    }

    private fun getParamValue(key: String): String? {
        return paramMap[key]
    }

    fun getParamValueAsInt(name: String, default: Int) : Int {
        val paramValue = getParamValue(name) ?: return default

        return try{
            paramValue.toInt()
        } catch (e : NumberFormatException) {
            default
        }
    }

    fun getKeywordTypeAsString(): String? {
        return paramMap["keywordType"]
    }

    fun getKeywordAsString(): String? {
        return paramMap["keyword"]
    }
}
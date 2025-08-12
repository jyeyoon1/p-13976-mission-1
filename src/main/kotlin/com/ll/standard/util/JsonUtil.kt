package com.ll.standard.util

object JsonUtil {
    fun listToString(list: List<Any?>): String {
        return list.joinToString(",\n", "[\n", "\n]") { it -> it.toString().prependIndent("    ") }
    }

    fun jsonStrToMap(jsonStr: String): Map<String, Any?> {
        return jsonStr
            .removeSurrounding("{", "}")
            .split(",")
            .mapNotNull {
                val (key, value) = it.split(":", limit = 2).map(String::trim).takeIf { it.size == 2 }
                    ?: return@mapNotNull null
                val formattedKey = key.removeSurrounding("\"")
                val formattedValue = if (value.startsWith("\"") && value.endsWith("\"")) {
                    value.removeSurrounding("\"")
                } else {
                    value.toInt()
                }
                formattedKey to formattedValue
            }.toMap()
    }

    fun mapToJson(map: Map<String, Any?>): String {
        return map.entries.joinToString(",\n", "{\n", "\n}") { (key, value) ->
            val formattedKey = "\"${key}\""
            val formattedValue = when(value) {
                is String -> "\"${value}\""
                else -> value
            }
            "    ${formattedKey}: $formattedValue"
        }
    }
}
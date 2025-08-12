package com.wiseSaying

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

object TestRunner {
    private val originIn: InputStream = System.`in`
    private val originOut: PrintStream = System.out
    fun run(input: String) : String {
        System.setIn(ByteArrayInputStream(("${input.trimIndent()}\n종료").toByteArray()))
        val output = ByteArrayOutputStream()
        val printStream = PrintStream(output)
        System.setOut(printStream)

        App().run()
        val result = output.toString().trim().replace("\\r\\n", "\n")
        System.setIn(originIn)
        System.setOut(originOut)
        return result
    }

    fun makeSampleData(size: Int) {
        val input = (1..size)
            .joinToString("\n") { i ->
                "등록\n명언 $i\n작자미상\n"
            }
        run(input)
    }
}
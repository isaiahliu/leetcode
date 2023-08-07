package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun interpret(command: String): String {
            return command.replace("(al)", "al").replace("()", "o")
        }
    }

    measureTimeMillis {
        Solution().interpret(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}


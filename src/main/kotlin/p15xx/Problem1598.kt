package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperations(logs: Array<String>): Int {
            var result = 0

            logs.forEach {
                when (it) {
                    "../" -> {
                        if (result > 0) {
                            result--
                        }
                    }

                    "./" -> {
                    }

                    else -> {
                        result++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minOperations(
            arrayOf("")
        ).also { println("${it} should be ${it}") }
    }
}


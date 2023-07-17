package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun printVertically(s: String): List<String> {
            val words = s.split(" ")

            val result = arrayListOf<String>()

            var index = 0

            while (true) {
                val sb = StringBuilder()
                for (word in words) {
                    sb.append(word.getOrElse(index) { ' ' })
                }

                val str = sb.trimEnd()

                if (str.isEmpty()) {
                    break
                }

                result.add(str.toString())

                index++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().printVertically(
            "HOW ARE YOU"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}


package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun generate(numRows: Int): List<List<Int>> {
            var previous = List(1) { 1 }

            val result = arrayListOf(previous)

            for (level in 1 until numRows) {
                previous = List(level + 1) {
                    when (it) {
                        0, level -> 1
                        else -> previous[it - 1] + previous[it]
                    }
                }

                result.add(previous)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().generate(3).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}


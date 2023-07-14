package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun oddCells(m: Int, n: Int, indices: Array<IntArray>): Int {
            val rowCount = IntArray(m)
            val columnCount = IntArray(n)

            indices.forEach { (r, c) ->
                rowCount[r]++
                columnCount[c]++
            }

            var result = 0
            repeat(m) { r ->
                repeat(n) { c ->
                    if ((rowCount[r] + columnCount[c]) % 2 == 1) {
                        result++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().oddCells(
            5, 5, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


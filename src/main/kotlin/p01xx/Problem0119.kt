package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getRow(rowIndex: Int): List<Int> {
            val result = MutableList(rowIndex + 1) { 1 }

            for (i in 1..rowIndex / 2) {
                result[i] = (result[i - 1].toLong() * (rowIndex - i + 1) / i).toInt()
            }

            for (i in rowIndex / 2 + 1 until rowIndex) {
                result[i] = result[rowIndex - i]
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getRow(5).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}


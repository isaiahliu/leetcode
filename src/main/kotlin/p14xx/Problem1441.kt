package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun buildArray(target: IntArray, n: Int): List<String> {
            val result = arrayListOf<String>()

            var next = 1
            var targetIndex = 0

            while (targetIndex < target.size) {
                result.add("Push")
                if (next == target[targetIndex]) {
                    targetIndex++
                } else {
                    result.add("Pop")
                }
                next++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().buildArray(
            intArrayOf(8, 2, 4, 7), 4
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}


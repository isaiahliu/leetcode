package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arrayRankTransform(arr: IntArray): IntArray {
            val sorted = arr.mapIndexed { index, num -> index to num }.sortedBy { it.second }

            var index = 1
            var pre = sorted.firstOrNull()?.second ?: 0
            sorted.forEach { (arrIndex, num) ->
                if (num != pre) {
                    index++
                    pre = num
                }
                arr[arrIndex] = index
            }

            return arr
        }
    }

    measureTimeMillis {
        Solution().arrayRankTransform(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


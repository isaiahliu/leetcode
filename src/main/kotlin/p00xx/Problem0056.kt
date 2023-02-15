package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun merge(intervals: Array<IntArray>): Array<IntArray> {
            intervals.sortBy { it[0] }

            var current = intervals[0]
            val result = arrayListOf(current)

            intervals.forEach { arr ->
                if (arr[0] <= current[1]) {
                    current[1] = current[1].coerceAtLeast(arr[1])
                } else {
                    current = arr
                    result.add(current)
                }
            }

            return result.toTypedArray()
        }
    }

    measureTimeMillis {
        println(Solution().merge(arrayOf(intArrayOf(1, 2, 3, 4))))
    }.also { println("Time cost: ${it}ms") }
}



package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
            if (intervals.isEmpty()) {
                return arrayOf(newInterval)
            }

            val result = arrayListOf<IntArray>()
            var arrIndex = 0
            while (arrIndex < intervals.size && intervals[arrIndex][1] < newInterval[0]) {
                result.add(intervals[arrIndex++])
            }

            if (arrIndex == intervals.size) {
                result.add(newInterval)
            } else {
                var current = if (newInterval[0] < intervals[arrIndex][0]) {
                    newInterval
                } else {
                    intervals[arrIndex].also {
                        intervals[arrIndex] = newInterval
                    }
                }
                result.add(current)

                while (arrIndex < intervals.size) {
                    val arr = intervals[arrIndex++]

                    if (arr[0] <= current[1]) {
                        current[1] = current[1].coerceAtLeast(arr[1])
                    } else {
                        current = arr
                        result.add(current)
                    }
                }
            }

            return result.toTypedArray()
        }
    }

    measureTimeMillis {
        println(Solution().insert(arrayOf(intArrayOf(1, 5)), intArrayOf(0, 3)))
    }.also { println("Time cost: ${it}ms") }
}



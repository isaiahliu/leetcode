package p34xx

import util.expect

fun main() {
    class Solution {
        fun maxFreeTime(eventTime: Int, k: Int, startTime: IntArray, endTime: IntArray): Int {
            var lastEndTime = 0

            var result = 0
            val distances = IntArray(startTime.size + 1) { index ->
                ((startTime.getOrNull(index) ?: eventTime) - lastEndTime).also {
                    if (index <= k) {
                        result += it
                    }
                    endTime.getOrNull(index)?.also {
                        lastEndTime = it
                    }
                }
            }

            var current = result
            var left = 0
            var right = k

            while (right < distances.lastIndex) {
                current -= distances[left++]
                current += distances[++right]

                result = maxOf(result, current)
            }

            return result
        }
    }

    expect {
        Solution().maxFreeTime(
            34, 2, intArrayOf(0, 17), intArrayOf(14, 19)
        )
    }
}

package p19xx

import util.expect

fun main() {
    class Solution {
        fun eliminateMaximum(dist: IntArray, speed: IntArray): Int {
            val sorted = dist.indices.map {
                dist[it] / speed[it] + if (dist[it] % speed[it] > 0) 1 else 0
            }.sorted()

            var result = 0

            for ((index, minute) in sorted.withIndex()) {
                if (minute > index) {
                    result++
                } else {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().eliminateMaximum(
            intArrayOf(-1, 0, 0, 1, 2), intArrayOf()
        )
    }
}
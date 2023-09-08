package p23xx

import util.expect

fun main() {
    class Solution {
        fun longestCycle(edges: IntArray): Int {
            var result = -1

            val max = edges.size * 100
            val times = IntArray(edges.size) { max }
            var time = 0

            edges.indices.forEach {
                var next = it
                val startTime = time
                while (next >= 0 && times[next] >= startTime) {
                    if (times[next] > time) {
                        times[next] = time++
                        next = edges[next]
                    } else {
                        result = result.coerceAtLeast(time - times[next])
                        break
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().longestCycle(
            intArrayOf(3, 4, 0, 2, -1, 2)
        )
    }
}
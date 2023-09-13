package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
            if (k >= costs.size) {
                return costs.fold(0L) { a, b -> a + b }
            }

            var left = 0
            var right = costs.lastIndex

            val queue = PriorityQueue(compareBy<Int> { costs[it] }.thenBy { it })

            var t = 0
            while (t++ < candidates && left <= right) {
                queue.add(left++)

                if (right >= left) {
                    queue.add(right--)
                }
            }

            var result = 0L

            repeat(k) {
                val next = queue.poll()
                result += costs[next]

                if (left <= right) {
                    if (next <= left) {
                        queue.add(left++)
                    } else {
                        queue.add(right--)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().totalCost(
            intArrayOf(10, 1, 11, 10), 2, 1
        )
    }
}
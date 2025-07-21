package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minCost(basket1: IntArray, basket2: IntArray): Long {
            val totalCounts = hashMapOf<Int, Int>()
            val basket1Counts = hashMapOf<Int, Int>()

            var min = Int.MAX_VALUE
            basket1.forEach {
                totalCounts[it] = (totalCounts[it] ?: 0) + 1
                basket1Counts[it] = (basket1Counts[it] ?: 0) + 1
                min = minOf(min, it)
            }
            basket2.forEach {
                totalCounts[it] = (totalCounts[it] ?: 0) + 1
                min = minOf(min, it)
            }
            min *= 2

            var result = 0L

            val queue1 = PriorityQueue<Int>()
            val queue2 = PriorityQueue<Int>(compareByDescending { it })

            totalCounts.forEach { (key, count) ->
                if (count % 2 == 1) {
                    return -1
                }

                val target = count / 2
                val b1 = basket1Counts[key] ?: 0

                repeat(target - b1) {
                    queue1.add(key)
                }
                repeat(b1 - target) {
                    queue2.add(key)
                }
            }

            while (queue1.isNotEmpty()) {
                result += minOf(queue1.poll(), queue2.poll(), min)
            }

            return result
        }
    }

    expect {
        Solution().minCost(
            intArrayOf(4, 2, 2, 2), intArrayOf(1, 4, 1, 2)
        )
    }
}

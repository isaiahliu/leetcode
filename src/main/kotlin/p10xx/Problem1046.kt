package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun lastStoneWeight(stones: IntArray): Int {
            val pq = PriorityQueue<Int>(compareByDescending { it })

            stones.forEach { pq.add(it) }

            while (true) {
                val m1 = pq.poll() ?: return 0
                val m2 = pq.poll() ?: return m1

                (m1 - m2).takeIf { it > 0 }?.also { pq.offer(it) }
            }
        }
    }

    expect {
        Solution().lastStoneWeight(
            intArrayOf(1, 2, 3)
        )
    }
}
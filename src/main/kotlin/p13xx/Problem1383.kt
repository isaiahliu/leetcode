package p13xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxPerformance(n: Int, speed: IntArray, efficiency: IntArray, k: Int): Int {
            val m = 1000000007

            val speedQueue = PriorityQueue<Int>(compareBy { speed[it] })
            val efficiencyQueue =
                PriorityQueue(compareByDescending<Int> { efficiency[it] }.thenByDescending { speed[it] })

            (0 until n).forEach { efficiencyQueue.add(it) }

            var result = 0L
            var speeds = 0L
            while (true) {
                val id = efficiencyQueue.poll() ?: break

                val eff = efficiency[id]

                speeds += speed[id]
                result = result.coerceAtLeast(speeds * eff)

                speedQueue.add(id)

                if (speedQueue.size == k) {
                    speeds -= speed[speedQueue.poll()]
                }
            }

            return (result % m).toInt()
        }
    }

    expect {
        Solution().maxPerformance(
            4, intArrayOf(8, 9, 5, 9), intArrayOf(1, 2, 6, 9), 2
        )
    }
}


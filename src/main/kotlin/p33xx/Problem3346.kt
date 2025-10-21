package p33xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        val REMOVE = 0
        val ADD = 1
        val STRICT = 2
        fun maxFrequency(nums: IntArray, k: Int, numOperations: Int): Int {
            val queue = PriorityQueue(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

            nums.forEach {
                queue.add(it - k to ADD)
                queue.add(it + k + 1 to REMOVE)
                queue.add(it to STRICT)
            }

            var adjCount = 0
            var numCount = 0
            var num = queue.peek().first
            var result = 0
            while (queue.isNotEmpty()) {
                val (n, count) = queue.poll()

                when (count) {
                    REMOVE -> adjCount--
                    ADD -> {
                        adjCount++
                        result = maxOf(result, minOf(numOperations, adjCount))
                    }

                    STRICT -> {
                        if (num != n) {
                            num = n
                            numCount = 0
                        }
                        numCount++

                        result = maxOf(result, numCount + minOf(numOperations, adjCount - numCount))
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxFrequency(
            intArrayOf(10, 13, 18, 27, 64, 65, 68, 82, 99), 27, 9
        )
    }
}

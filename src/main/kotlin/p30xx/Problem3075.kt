package p30xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
            val priorityQueue = PriorityQueue<Int>()

            happiness.forEach {
                priorityQueue.add(it)

                if (priorityQueue.size > k) {
                    priorityQueue.poll()
                }
            }

            var result = 0L

            var sub = k - 1
            while (priorityQueue.isNotEmpty()) {
                result += maxOf(priorityQueue.poll() - sub--, 0)
            }

            return result
        }
    }

    expect {
        Solution().maximumHappinessSum(
            intArrayOf(), 1
        )
    }
}

package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestSubarray(nums: IntArray, k: Int): Int {
            var result = Int.MAX_VALUE

            val sumRange = LinkedList<Pair<Long, Int>>()
            sumRange += 0L to -1

            var sum = 0L
            repeat(nums.size) { index ->
                sum += nums[index]

                while (true) {
                    val (preSum, preIndex) = sumRange.peekFirst() ?: break

                    if (preSum + k <= sum) {
                        result = result.coerceAtMost(index - sumRange.pollFirst().second)
                    } else if (index - preIndex >= result) {
                        sumRange.pollFirst()
                    } else {
                        break
                    }
                }

                while (true) {
                    sumRange.peekLast()?.takeIf { it.first >= sum } ?: break

                    sumRange.pollLast()
                }

                sumRange.add(sum to index)
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    measureTimeMillis {
        Solution().shortestSubarray(
            intArrayOf(
                1, 2
            ), 4
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}
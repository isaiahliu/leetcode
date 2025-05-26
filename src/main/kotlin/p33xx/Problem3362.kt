package p33xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {
            val leftQueue = PriorityQueue<IntArray>(compareBy { it[0] })
            val rightQueue = PriorityQueue<Int>(compareByDescending { it })

            queries.forEach {
                leftQueue.offer(it)
            }

            val sums = IntArray(nums.size + 1)

            var result = queries.size
            nums.forEachIndexed { index, num ->
                while (leftQueue.isNotEmpty() && leftQueue.peek()[0] <= index) {
                    rightQueue.offer(leftQueue.poll()[1])
                }

                sums.getOrNull(index - 1)?.also {
                    sums[index] += it
                }

                repeat(num - sums[index]) {
                    if (rightQueue.isEmpty || rightQueue.peek() < index) {
                        return -1
                    }

                    result--

                    sums[index]++
                    sums[rightQueue.poll() + 1]--
                }
            }

            return result
        }
    }

    expect {
        Solution().maxRemoval(
            intArrayOf(5), arrayOf(intArrayOf(0, 0, 5))
        )
    }
}

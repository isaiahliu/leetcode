package p30xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, k: Int): Int {
            val queue = PriorityQueue<Long>()

            nums.forEach {
                queue.add(it.toLong())
            }

            var result = 0

            while (queue.peek() < k) {
                val n1 = queue.poll()
                val n2 = queue.poll() ?: break

                queue.add(n1 * 2 + n2)
                result++
            }


            return result
        }
    }
    expect {
        Solution().minOperations(
            intArrayOf(1), 1
        )
    }
}

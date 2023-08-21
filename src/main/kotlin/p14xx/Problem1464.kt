package p14xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxProduct(nums: IntArray): Int {
            val queue = PriorityQueue<Int>()

            nums.forEach {
                queue.offer(it - 1)

                if (queue.size > 2) {
                    queue.poll()
                }
            }

            return queue.poll() * queue.poll()
        }
    }

    expect {
        Solution().maxProduct(
            intArrayOf()
        )

    }
}


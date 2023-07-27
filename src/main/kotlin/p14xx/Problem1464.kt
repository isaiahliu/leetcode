package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().maxProduct(
            intArrayOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}


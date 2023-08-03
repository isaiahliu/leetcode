package p15xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSumRangeQuery(nums: IntArray, requests: Array<IntArray>): Int {
            val counts = IntArray(nums.size)

            requests.forEach { (from, to) ->
                counts[from]++
                (to + 1).takeIf { it < nums.size }?.also { counts[it]-- }
            }

            var sum = 0
            val countQueue = PriorityQueue<Int>(compareByDescending { it })
            counts.forEach {
                sum += it

                if (sum > 0) {
                    countQueue.offer(sum)
                }
            }

            val m = 1000000007
            var result = 0L
            for (num in nums.sortedByDescending { it }) {
                result += num.toLong() * (countQueue.poll() ?: break)
                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().maxSumRangeQuery(
            intArrayOf(), arrayOf()
        ).also { println("${it} should be ${it}") }
    }
}


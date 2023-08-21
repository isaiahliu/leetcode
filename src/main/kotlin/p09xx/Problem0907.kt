package p09xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun sumSubarrayMins(arr: IntArray): Int {
            val m = 1000000007

            val stack = LinkedList<IntArray>()
            val decCounts = IntArray(arr.size)
            for (i in arr.indices) {
                val num = arr[i]

                var count = 1
                while (stack.peek()?.takeIf { it[0] >= num } != null) {
                    count += stack.poll()[1]
                }

                stack.push(intArrayOf(num, count))
                decCounts[i] = count
            }

            stack.clear()
            val incCounts = IntArray(arr.size)
            for (i in arr.lastIndex downTo 0) {
                val num = arr[i]

                var count = 1
                while (stack.peek()?.takeIf { it[0] > num } != null) {
                    count += stack.poll()[1]
                }

                stack.push(intArrayOf(num, count))
                incCounts[i] = count
            }

            var result = 0L
            arr.indices.forEach {
                result += 1L * decCounts[it] * incCounts[it] * arr[it]
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().sumSubarrayMins(
            intArrayOf(71, 55, 82, 55)
        )
    }
}
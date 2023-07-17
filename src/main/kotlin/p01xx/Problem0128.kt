package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestConsecutive(nums: IntArray): Int {
            val set = nums.toMutableSet()

            var result = 0
            while (set.size > result) {
                val num = set.first()

                var length = 1

                set.remove(num)

                var t = num - 1
                while (t in set) {
                    set.remove(t--)
                    length++
                }

                t = num + 1
                while (t in set) {
                    set.remove(t++)
                    length++
                }

                result = result.coerceAtLeast(length)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestConsecutive(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


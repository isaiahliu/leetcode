package p24xx

import util.expect

fun main() {
    class Solution {
        fun countDistinctIntegers(nums: IntArray): Int {
            val set = hashSetOf<Int>()

            fun Int.reverse(): Int {
                var result = 0
                var t = this
                while (t > 0) {
                    result *= 10
                    result += t % 10
                    t /= 10
                }

                return result
            }
            nums.forEach {
                set.add(it)
                set.add(it.reverse())
            }

            return set.size
        }
    }

    expect {
        Solution().countDistinctIntegers(
            intArrayOf(1, 13, 10, 12, 31)
        )
    }
}
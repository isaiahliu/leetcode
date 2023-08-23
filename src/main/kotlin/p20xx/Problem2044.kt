package p20xx

import util.expect

fun main() {
    class Solution {
        fun countMaxOrSubsets(nums: IntArray): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            var max = 0
            var result = 0
            repeat(1 shl nums.size) {
                var sum = 0
                it.forEachBit {
                    sum = sum or nums[it]
                }

                if (sum > max) {
                    max = sum
                    result = 0
                }

                if (sum == max) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().countMaxOrSubsets(
            intArrayOf(3, 1)
        )
    }
}
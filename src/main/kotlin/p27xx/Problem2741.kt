package p27xx

import util.expect

fun main() {
    class Solution {
        fun specialPerm(nums: IntArray): Int {
            val m = 1000000007L
            val dp = Array(1 shl nums.size) {
                hashMapOf<Int, Long>()
            }

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

            (1 until (1 shl nums.size)).forEach {
                it.forEachBit { p ->
                    val prev = it - (1 shl p)

                    dp[it][p] = if (prev == 0) {
                        1L
                    } else {
                        val num = nums[p]

                        dp[prev].filterKeys {
                            num % nums[it] == 0 || nums[it] % num == 0
                        }.values.sum() % m
                    }
                }
            }

            return (dp[(1 shl nums.size) - 1].values.sum() % m).toInt()
        }
    }

    expect {
        Solution().specialPerm(
            intArrayOf(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192)
        )
    }
}

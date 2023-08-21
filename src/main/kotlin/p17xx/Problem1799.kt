package p17xx

import util.expect

fun main() {
    class Solution {
        fun maxScore(nums: IntArray): Int {
            val dp = IntArray(1 shl nums.size)

            fun Int.bitPos(): List<Int> {
                var t = this

                val result = arrayListOf<Int>()
                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        result.add(index)
                    }

                    t /= 2
                    index++
                }
                return result
            }


            repeat(dp.size) { status ->
                val bitCount = Integer.bitCount(status)
                if (bitCount.let { it > 0 && it % 2 == 0 }) {
                    val picked = status.bitPos()

                    val times = bitCount / 2
                    var result = 0
                    picked.forEachIndexed { index, pos1 ->
                        for (index2 in index + 1 until picked.size) {
                            val pos2 = picked[index2]

                            result = result.coerceAtLeast(
                                nums[pos1].toBigInteger().gcd(nums[pos2].toBigInteger())
                                    .toInt() * times + dp[status - (1 shl pos1) - (1 shl pos2)]
                            )
                        }
                    }

                    dp[status] = result
                }
            }

            return dp.last()
        }
    }

    expect {
        Solution().maxScore(
            intArrayOf(1, 4, 10, 3, 1)
        )
    }
}

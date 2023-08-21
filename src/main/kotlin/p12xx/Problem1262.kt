package p12xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxSumDivThree(nums: IntArray): Int {
            val dp = Array(nums.size) { intArrayOf(0, Int.MIN_VALUE, Int.MIN_VALUE) }

            for (i in nums.indices) {
                val num = nums[i]
                dp[i][num % 3] = num

                dp.getOrNull(i - 1)?.also { lastDp ->
                    dp[i].forEachIndexed { index, d ->
                        dp[i][index] =
                            d.coerceAtLeast(lastDp[(3 + index - (num % 3)) % 3] + num).coerceAtLeast(lastDp[index])

                    }
                }
            }

            return dp[dp.lastIndex][0]
        }

        fun maxSumDivThree2(nums: IntArray): Int {
            val result = nums.sum()
            val m = result % 3

            if (m == 0) {
                return result
            }

            nums.sort()

            val heap = TreeSet<Int>()

            var minSum = Int.MAX_VALUE
            for (num in nums) {
                when {
                    num % 3 == 0 -> {
                        continue
                    }

                    num > minSum -> {
                        break
                    }

                    num % 3 == m -> {
                        minSum = num
                        break
                    }

                    else -> {
                        for (s in heap.toSet()) {
                            val t = num + s
                            when (t % 3) {
                                0 -> {
                                }

                                m -> {
                                    minSum = t
                                }

                                else -> {
                                    heap.add(t)
                                }
                            }
                        }

                        heap.add(num)
                    }
                }

            }

            return result - minSum
        }
    }

    expect {
        Solution().maxSumDivThree(
            intArrayOf(
                3, 6, 5, 1, 8
            )
        )
    }
}
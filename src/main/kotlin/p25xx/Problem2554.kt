package p25xx

import util.expect

fun main() {
    class Solution {
        fun maxCount(banned: IntArray, n: Int, maxSum: Int): Int {
            banned.sort()
            var banIndex = 0

            var sum = 0L
            var result = 0
            for (num in 1..n) {
                when {
                    sum + num > maxSum -> {
                        break
                    }

                    banIndex == banned.size || num < banned[banIndex] -> {
                        sum += num
                        result++
                    }

                    else -> {
                        while (banIndex < banned.size && num == banned[banIndex]) {
                            banIndex++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxCount(
            intArrayOf(1, 3, 2, 4, 5), 1, 1
        )
    }
}
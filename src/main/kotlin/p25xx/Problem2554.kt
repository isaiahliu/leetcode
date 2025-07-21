package p25xx

import util.expect

fun main() {
    class Solution {
        fun maxCount(banned: IntArray, n: Int, maxSum: Int): Int {
            val bannedSet = banned.toMutableSet()

            var sum = 0L
            var result = 0
            for (num in 1..n) {
                when {
                    sum + num > maxSum -> {
                        break
                    }

                    !bannedSet.remove(num) -> {
                        sum += num
                        result++
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
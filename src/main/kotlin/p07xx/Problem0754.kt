package p07xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun reachNumber(target: Int): Int {
            val posTarget = target.absoluteValue

            fun binarySearch(start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val midNum = (start + end) / 2
                val midSum = (1 + midNum) * midNum / 2

                return when {
                    midSum == posTarget -> {
                        return midNum
                    }

                    midSum < posTarget -> {
                        binarySearch(midNum + 1, end)
                    }

                    else -> {
                        binarySearch(start, midNum - 1) ?: midNum
                    }
                }
            }

            val num = binarySearch(1, 45000) ?: return 0
            val sum = (1 + num) * num / 2

            return if (sum > posTarget && (sum - posTarget) % 2 == 1) {
                num + (num % 2) + 1
            } else {
                num
            }
        }
    }

    expect {
        Solution().reachNumber(
            5
        )
    }
}
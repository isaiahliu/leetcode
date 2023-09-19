package p24xx

import util.expect

fun main() {
    class Solution {
        fun countPalindromes(s: String): Int {
            val m = 1000000007
            val leftSingleCounts = IntArray(10)
            val leftDoubleCounts = Array(s.length) { LongArray(100) }

            leftSingleCounts[s[0] - '0']++

            for (index in 1 until s.length) {
                val num = s[index] - '0'
                repeat(100) {
                    leftDoubleCounts[index][it] = leftDoubleCounts[index - 1][it]
                }

                leftSingleCounts.forEachIndexed { left, count ->
                    leftDoubleCounts[index][left * 10 + num] += count.toLong()
                }

                leftSingleCounts[num]++
            }

            val rightSingleCounts = IntArray(10)
            val rightDoubleCounts = Array(s.length) { LongArray(100) }

            rightSingleCounts[s.last() - '0']++

            for (index in s.lastIndex - 1 downTo 0) {
                val num = s[index] - '0'
                repeat(100) {
                    rightDoubleCounts[index][it] = rightDoubleCounts[index + 1][it]
                }

                rightSingleCounts.forEachIndexed { right, count ->
                    rightDoubleCounts[index][right * 10 + num] += count.toLong()
                }

                rightSingleCounts[num]++
            }

            var result = 0L

            (1 until s.lastIndex).forEach {
                val left = leftDoubleCounts[it - 1]
                val right = rightDoubleCounts[it + 1]

                left.forEachIndexed { index, l ->
                    result += l * right[index]
                    result %= m
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countPalindromes(
            "103301"
        )
    }
}

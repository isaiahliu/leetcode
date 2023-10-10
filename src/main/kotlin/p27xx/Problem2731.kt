package p27xx

import util.expect

fun main() {
    class Solution {
        fun sumDistance(nums: IntArray, s: String, d: Int): Int {
            var sum = 0L
            var result = 0L
            val m = 1000000007

            nums.mapIndexed { index, pos ->
                if (s[index] == 'L') {
                    pos - d
                } else {
                    pos + d
                }
            }.sorted().forEachIndexed { index, pos ->
                result += pos * index.toLong() - sum
                result %= m

                sum += pos
            }

            return result.toInt()
        }
    }

    expect {
        Solution().sumDistance(
            intArrayOf(), "", 3
        )
    }
}
